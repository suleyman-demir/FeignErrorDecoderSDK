package com.sdkcourier.feignErrorDecoderSdk.exception;

public enum ErrorCode {
    JSON_PROCESSING_ERROR(500, "JSON_PROCESSING_ERROR"),
    REQUEST_ERROR(500, "REQUEST_ERROR"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    UNKNOWN_ERROR(0, "UNKNOWN_ERROR");

    private final int statusCode;
    private final String errorCode;


    ErrorCode(int statusCode, String errorCode) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static ErrorCode fromStatusCode(int statusCode) {
        for (ErrorCode code : values()) {
            if (code.getStatusCode() == statusCode) {
                return code;
            }
        }
        return UNKNOWN_ERROR;
    }
}
