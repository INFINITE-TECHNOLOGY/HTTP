package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox

@BlackBox
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderDefaultHttp extends SenderDefault {

    @Override
    HttpResponse sendHttpMessage(HttpRequest httpRequest) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection(httpRequest)
        log.warn("UNSECURE TEST PLAINTEXT HTTP CONNECTION")
        log.warn("DO NOT USE ON PRODUCTION")
        if (httpURLConnection.getURL().protocol.contains("https")) {
            throw new HttpException("Invalid protocol 'https' for SenderDefaultHttp in ${httpRequest.url}. Use 'http' protocol.")
        }
        return super.sendHttpMessageWithUrlConnection(httpRequest, httpURLConnection)
    }

}