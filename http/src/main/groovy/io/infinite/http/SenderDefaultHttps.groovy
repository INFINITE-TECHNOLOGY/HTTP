package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.carburetor.CarburetorLevel

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLSocketFactory

@BlackBox(level = CarburetorLevel.ERROR)
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderDefaultHttps extends SenderDefault {

    @Override
    HttpResponse sendHttpMessage(HttpRequest httpRequest) {
        HttpsURLConnection.defaultSSLSocketFactory = SSLSocketFactory.default as SSLSocketFactory
        HttpURLConnection httpURLConnection = (HttpsURLConnection) openConnection(httpRequest)
        return super.sendHttpMessageWithUrlConnection(httpRequest, httpURLConnection)
    }

}