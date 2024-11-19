package com.sdkcourier.feignErrorDecoderSdk.exception;

import com.sdkcourier.feignErrorDecoderSdk.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for centralized error handling.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles CustomException.
     *
     * @param ex CustomException instance.
     * @return ResponseEntity with ErrorResponse and HTTP status.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .statusCode(ex.getStatusCode())
                .build();


        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatusCode()));
    }

    /**
     * Handles generic Exception.
     *
     * @param ex Generic Exception instance.
     * @return ResponseEntity with ErrorResponse and HTTP status.
     */
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
