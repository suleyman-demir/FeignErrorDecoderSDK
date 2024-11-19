package com.sdkcourier.feignErrorDecoderSdk.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

/**
 * Feign Client için özel bir hata decoder.
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorCode errorCode = ErrorCode.fromStatusCode(response.status());
        String message = String.format("Error occurred on %s with status %d", methodKey, response.status());

        return new CustomException(message, response.status(), errorCode.getErrorCode());
    }
}
