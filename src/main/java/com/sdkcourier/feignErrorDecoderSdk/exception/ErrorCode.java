package com.sdkcourier.feignErrorDecoderSdk.exception;



/**
 * HTTP statü kodları ve özelleştirilmiş hata kodları için enum.
 */
public enum ErrorCode {
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
