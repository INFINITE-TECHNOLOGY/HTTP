package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.carburetor.CarburetorLevel

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLContext
import java.security.SecureRandom

@BlackBox(level = CarburetorLevel.ERROR)
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderDefaultHttpsUnsecure extends SenderDefault {

    @Override
    HttpResponse sendHttpMessage(HttpRequest httpRequest) {
        SSLContext sslContext = SSLContext.getInstance("TLS")
        UnsecureTrustManager[] unsecureTrustManagers = new UnsecureTrustManager() as UnsecureTrustManager[]
        sslContext.init(null as KeyManager[], unsecureTrustManagers, null as SecureRandom)
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection(httpRequest)
        httpsURLConnection.SSLSocketFactory = sslContext.socketFactory
        httpsURLConnection.hostnameVerifier = new UnsecureHostNameVerifier()
        log.warn("UNSECURE TEST TLS MODE IS USED")
        log.warn("DO NOT USE ON PRODUCTION")
        return super.sendHttpMessageWithUrlConnection(httpRequest, httpsURLConnection)
    }

}