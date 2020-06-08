package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.carburetor.CarburetorLevel

@BlackBox(level = CarburetorLevel.ERROR)
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderDefaultHttps extends SenderDefault {

    @Override
    HttpResponse sendHttpMessage(HttpRequest httpRequest) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection(httpRequest)
        return super.sendHttpMessageWithUrlConnection(httpRequest, httpURLConnection)
    }

}