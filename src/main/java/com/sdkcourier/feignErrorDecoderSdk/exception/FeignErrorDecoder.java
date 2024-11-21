package com.sdkcourier.feignErrorDecoderSdk.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;


@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String message = String.format("Error in method %s with status %d", methodKey, response.status());
        return new RuntimeException(message);
    }
}
