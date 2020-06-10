package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.blackbox.BlackBoxLevel

import javax.net.ssl.HttpsURLConnection

@BlackBox(level = BlackBoxLevel.ERROR)
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderDefaultHttps extends SenderDefault {

    @Override
    HttpResponse sendHttpMessage(HttpRequest httpRequest) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection(httpRequest)
        if (!(httpURLConnection instanceof HttpsURLConnection)) {
            log.warn("UNSECURE TEST PLAINTEXT HTTP CONNECTION")
            log.warn("DO NOT USE ON PRODUCTION")
        }
        return super.sendHttpMessageWithUrlConnection(httpRequest, httpURLConnection)
    }

}