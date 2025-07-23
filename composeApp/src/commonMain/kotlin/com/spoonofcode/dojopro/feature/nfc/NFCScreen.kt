package com.spoonofcode.dojopro.feature.nfc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.spoonofcode.dojopro.core.nfc.NfcReader
import com.spoonofcode.dojopro.core.nfc.rememberNfcReader
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.nfc
import org.jetbrains.compose.resources.StringResource
import org.koin.compose.viewmodel.koinViewModel

internal class NFCScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.nfc,
) : BaseScreen<NFCViewModel, NFCViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<NFCViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: NFCViewModel,
        viewState: NFCViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
//            viewModel.initView()
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: NFCViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BLB("HOME")

            NfcScreen()
        }
    }

    @Composable
    fun NfcScreen(reader: NfcReader = rememberNfcReader()) {
        val tag by reader.tags.collectAsState(initial = null)

        LaunchedEffect(Unit) { reader.start() }
        DisposableEffect(Unit) { onDispose { reader.stop() } }

        Column {
            Texts.BLB("Tap an NFC tag")
            tag?.let {
                Texts.BLB("ID  : ${it.id}")
                Texts.BLB("Tech: ${it.techList.joinToString()}")
                Texts.BLB("ID  : ${it.id}")
                it.ndef?.let { ndef ->
                    Texts.BLB("NDEF: $ndef")
                }
            } ?: Texts.BLB("Waiting…")
        }
    }

//    @Preview
//    @Composable
//    private fun ChatScreenPreview() {
//        ContentView(
//            snackbarHostState = remember { SnackbarHostState() },
//            content = ContentView(
//                viewState = ChatViewState(),
//            )
//        )
//    }
}