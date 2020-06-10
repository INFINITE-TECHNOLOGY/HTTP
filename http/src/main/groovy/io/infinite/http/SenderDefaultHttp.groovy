package io.infinite.http

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import io.infinite.blackbox.BlackBox
import io.infinite.blackbox.BlackBoxLevel

@BlackBox(level = BlackBoxLevel.ERROR)
@ToString(includeNames = true, includeFields = true, includeSuper = true)
@Slf4j
class SenderDefaultHttp extends SenderDefaultHttps {

}