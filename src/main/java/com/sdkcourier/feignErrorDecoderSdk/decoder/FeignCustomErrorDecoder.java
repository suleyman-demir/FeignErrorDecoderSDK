package com.sdkcourier.feignErrorDecoderSdk.decoder;

import com.sdkcourier.feignErrorDecoderSdk.exception.InternalClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FeignCustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 500 && response.status() <= 599) {
            log.error("Server error (5xx): {}, request url: {}", getResponseBody(response), response.request().url());
            return new InternalClientException("Internal server error occurred.");
        }


        return defaultErrorDecoder.decode(methodKey, response);
    }



    private String getResponseBody(Response response) {
        try {
            if (response.body() != null) {
                return response.body().toString();
            }
        } catch (Exception e) {
            log.error("Error reading response body: {}", e.getMessage());
        }
        return null;
    }
}
