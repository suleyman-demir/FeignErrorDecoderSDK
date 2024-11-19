package com.sdkcourier.feignErrorDecoderSdk.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String errorCode;
    private Integer statusCode;

}
