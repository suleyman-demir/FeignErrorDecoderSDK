package com.sdkcourier.feignErrorDecoderSdk.exception;


import lombok.Getter;

/**
 * HttpClient tabanlı özel bir exception sınıfı.
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

}
