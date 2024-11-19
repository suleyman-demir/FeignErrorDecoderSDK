package com.sdkcourier.feignErrorDecoderSdk.exception;

import lombok.Getter;

/**
 * Özelleştirilmiş exception sınıfı.
 */
@Getter
public class CustomException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;

    public CustomException(String message, int statusCode, String errorCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public CustomException(String message, int statusCode, String errorCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}
