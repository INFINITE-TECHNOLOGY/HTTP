package io.infinite.http

class HttpException extends Exception {

    HttpException(String var1) {
        super(var1)
    }

    HttpException(String var1, Exception exception) {
        super(var1, exception)
    }

}