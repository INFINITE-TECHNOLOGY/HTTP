package io.infinite.http

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class HttpRequest extends HttpMessageAbstract {

    String method
    String url
    String requestStatus
    String exceptionString
    final Date sendDate = new Date()
    Map<String, Object> httpProperties = new HashMap<>()
    Map<String, Object> extensions = new HashMap<>()
    Integer connectTimeout = 15000
    Integer readTimeout = 15000

}
