package com.spoonofcode.dojopro

import javax.net.ssl.X509TrustManager

internal class AllCertsTrustManager : X509TrustManager {


    @Suppress("TrustAllX509TrustManager")
    override fun checkClientTrusted(
        chain: Array<out java.security.cert.X509Certificate>?,
        authType: String?
    ) {
        // no-op
    }

    @Suppress("TrustAllX509TrustManager")
    override fun checkServerTrusted(
        chain: Array<out java.security.cert.X509Certificate>?,
        authType: String?
    ) {
        // no-op
    }

    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
}