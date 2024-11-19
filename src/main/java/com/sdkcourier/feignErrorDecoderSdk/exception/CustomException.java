package com.sdkcourier.feignErrorDecoderSdk.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * HttpClient tabanlı özel bir exception sınıfı.
 */
@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final String message;
    private final Integer statusCode;
    private final String errorCode;


}
