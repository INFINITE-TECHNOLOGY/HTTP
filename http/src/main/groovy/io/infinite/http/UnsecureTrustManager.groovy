package io.infinite.http


import groovy.transform.ToString
import io.infinite.blackbox.BlackBox
import io.infinite.blackbox.BlackBoxLevel

import javax.net.ssl.X509TrustManager
import java.security.cert.X509Certificate

@ToString(includeNames = true, includeFields = true)
@BlackBox(level = BlackBoxLevel.METHOD)
class UnsecureTrustManager implements X509TrustManager {

    @Override
    X509Certificate[] getAcceptedIssuers() {
        return null as X509Certificate[]
    }

    @Override
    void checkClientTrusted(X509Certificate[] certs, String authType) {
    }

    @Override
    void checkServerTrusted(X509Certificate[] certs, String authType) {
    }

}
