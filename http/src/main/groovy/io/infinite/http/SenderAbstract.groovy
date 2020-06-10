package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.blackbox.BlackBoxLevel
import io.infinite.supplies.ast.exceptions.ExceptionUtils

@BlackBox(level = BlackBoxLevel.METHOD)
@ToString(includeNames = true, includeFields = true)
@Slf4j
abstract class SenderAbstract {

    abstract HttpResponse sendHttpMessage(HttpRequest httpRequest)

    HttpResponse expectStatus(HttpRequest httpRequest, Integer expectedStatus) {
        HttpResponse httpResponse = sendHttpMessage(httpRequest)
        if (httpResponse.status != expectedStatus) {
            throw new HttpException("Failed HTTP Response code: ${httpResponse.status}")
        }
        return httpResponse
    }

    void fail(HttpRequest httpRequest, Exception connectException, HttpMessageStatuses messageStatus) {
        httpRequest.exceptionString = new ExceptionUtils().stacktrace(connectException)
        log.warn("Exception during sending:")
        log.warn(httpRequest.exceptionString)
        httpRequest.requestStatus = messageStatus.value()
    }

}
