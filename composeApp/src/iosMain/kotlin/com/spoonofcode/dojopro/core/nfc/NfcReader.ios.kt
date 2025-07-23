package com.spoonofcode.dojopro.core.nfc

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreNFC.NFCFeliCaTagProtocol
import platform.CoreNFC.NFCISO15693TagProtocol
import platform.CoreNFC.NFCISO7816TagProtocol
import platform.CoreNFC.NFCMiFareTagProtocol
import platform.CoreNFC.NFCNDEFPayload
import platform.CoreNFC.NFCNDEFTagProtocol
import platform.CoreNFC.NFCPollingISO14443
import platform.CoreNFC.NFCPollingISO15693
import platform.CoreNFC.NFCPollingISO18092
import platform.CoreNFC.NFCTagProtocol
import platform.CoreNFC.NFCTagReaderSession
import platform.CoreNFC.NFCTagReaderSessionDelegateProtocol
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.darwin.NSObject

/* ---------- small platform helpers ---------- */

private fun ByteArray.toHex(): String =
    joinToString("") { (it.toInt() and 0xFF).toString(16).padStart(2, '0').uppercase() }

@OptIn(ExperimentalForeignApi::class)
private fun NSData.toByteArray(): ByteArray = memScoped {
    val size = length.toInt()
    val buf = bytes?.reinterpret<ByteVar>() ?: error("No bytes")
    ByteArray(size) { idx -> buf[idx] }
}

private fun NSData?.utf8(): String? =
    this?.let { NSString.create(it, NSUTF8StringEncoding) as String }

/* ---------- KOTLIN-ONLY reader that UI will use ---------- */

internal class IosNfcReader : NfcReader {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val _tags = MutableSharedFlow<NfcTag>(extraBufferCapacity = 1)
    override val tags: Flow<NfcTag> = _tags.asSharedFlow()

    private var session: NFCTagReaderSession? = null

    /** Delegate is Obj-C land → separated to avoid the mix-in error */
    private val delegate = object : NSObject(), NFCTagReaderSessionDelegateProtocol {

        override fun tagReaderSessionDidBecomeActive(session: NFCTagReaderSession) = Unit

        override fun tagReaderSession(
            session: NFCTagReaderSession,
            didInvalidateWithError: NSError
        ) {
            this@IosNfcReader.session = null
        }

        @Suppress("UNCHECKED_CAST")
        override fun tagReaderSession(
            session: NFCTagReaderSession,
            didDetectTags: List<*>
        ) {
            val tag = (didDetectTags.firstOrNull() as? NFCTagProtocol) ?: return

            session.connectToTag(tag) { err ->
                if (err != null) {
                    session.invalidateSessionWithErrorMessage(
                        err.localizedDescription ?: "NFC error"
                    )
                    return@connectToTag
                }

                scope.launch {
                    /* ------- UID + tech string ------- */
                    val (uid, tech) = when (tag) {
                        is NFCMiFareTagProtocol -> tag.identifier.toByteArray().toHex() to "MIFARE"
                        is NFCISO15693TagProtocol -> tag.identifier.toByteArray()
                            .toHex() to "ISO15693"

                        is NFCISO7816TagProtocol -> tag.identifier.toByteArray()
                            .toHex() to "ISO7816"

                        is NFCFeliCaTagProtocol -> tag.currentIDm.toByteArray().toHex() to "FeliCa"
                        else -> "" to "Unknown"
                    }

                    /* ------- optional first NDEF record ------- */
                    var ndef: String? = null
                    (tag as? NFCNDEFTagProtocol)?.let { ndefTag ->
                        suspendCancellableCoroutine<Unit> { cont ->
                            ndefTag.readNDEFWithCompletionHandler { msg, _ ->
                                val payloadData =
                                    (msg?.records?.firstOrNull() as? NFCNDEFPayload)?.payload
                                ndef = payloadData.utf8()
                                cont.resume(Unit) {}
                            }
                        }
                    }

                    _tags.emit(NfcTag(id = uid, techList = listOf(tech), ndef = ndef))
                    session.invalidateSession()          // auto-restart feels nicer
                }
            }
        }
    }

    /* ------------- NfcReader interface ------------- */
    override fun start() {
        if (session != null) return        // already active
        session = NFCTagReaderSession(
            pollingOption = NFCPollingISO14443 or
                    NFCPollingISO15693 or
                    NFCPollingISO18092,
            delegate = delegate,
            queue = null           // main thread
        ).apply {
            alertMessage = "Hold your iPhone near the NFC tag."
            beginSession()
        }
    }

    override fun stop() {
        session?.invalidateSession()
        session = null
    }
}

/* ---------- Compose helper ---------- */

@Composable
actual fun rememberNfcReader(): NfcReader = IosNfcReader()