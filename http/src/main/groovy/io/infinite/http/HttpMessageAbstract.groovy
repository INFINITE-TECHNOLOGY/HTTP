package io.infinite.http

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true)
class HttpMessageAbstract {

    HashMap<String, String> headers = new HashMap<>()
    String body

}
