package com.sdkcourier.feignErrorDecoderSdk.exception;

import com.sdkcourier.feignErrorDecoderSdk.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .statusCode(ex.getStatusCode())
                .build();


        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatusCode()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message("An unexpected error occurred: " + ex.getMessage())
                .errorCode("UNKNOWN_ERROR")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
