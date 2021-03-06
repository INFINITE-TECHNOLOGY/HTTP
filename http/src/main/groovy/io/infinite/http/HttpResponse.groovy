package io.infinite.http

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includeSuper = true)
class HttpResponse extends HttpMessageAbstract {

    Integer status
    final Date receiveDate = new Date()

}
