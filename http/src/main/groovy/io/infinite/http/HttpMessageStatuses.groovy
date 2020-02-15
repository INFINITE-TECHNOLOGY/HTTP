package io.infinite.http

enum HttpMessageStatuses {

    NEW("new"),
    NEW2("NEW"),
    DELIVERED("delivered"),
    FAILED_NO_CONNECTION("no_connection"),
    FAILED_RESPONSE("failed_response"),
    EXCEPTION("error"),
    WAITING("waiting"),
    DUPLICATE("duplicate"),
    RENEWED("renewed"),
    SENDING("sending"),
    SPLIT("split")

    private final String messageStatus

    HttpMessageStatuses(String iMessageStatus) {
        messageStatus = iMessageStatus
    }

    String value() {
        return messageStatus
    }

}