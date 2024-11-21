package com.sdkcourier.feignErrorDecoderSdk.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;


@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    UNKNOWN_ERROR(HttpStatus.UNAUTHORIZED.value(), "UNKNOWN_ERROR");

    private final Integer statusCode;
    private final String errorCode;


    public static ErrorCode fromStatusCode(int statusCode) {
        return Arrays.stream(ErrorCode.values())
                .filter(obj -> obj.getStatusCode().equals(statusCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status code: " + statusCode));
    }
}
