package io.infinite.http


import groovy.transform.ToString
import io.infinite.blackbox.BlackBox
import io.infinite.carburetor.CarburetorLevel

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@ToString(includeNames = true, includeFields = true)
@BlackBox(level = CarburetorLevel.METHOD)
class UnsecureHostNameVerifier implements HostnameVerifier {

    @Override
    boolean verify(String hostName, SSLSession sslSession) {
        return true
    }

}
