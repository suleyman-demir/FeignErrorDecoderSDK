package com.sdkcourier.feignErrorDecoderSdk.sdk;

import com.sdkcourier.feignErrorDecoderSdk.exception.CustomException;
import com.sdkcourier.feignErrorDecoderSdk.exception.ErrorCode;
import com.sdkcourier.feignErrorDecoderSdk.model.Courier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Courier Service SDK
 */
@Service
public class CourierServiceSDK {

    private final String baseUrl;
    private final HttpClient httpClient;

    public CourierServiceSDK(@Value("${courier.service.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return executeGetCourier(courierId);
            } catch (CustomException e) {
                throw new RuntimeException("Async getCourier failed", e);
            }
        });
    }

    public Courier executeGetCourier(Long courierId) throws CustomException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/getCourier/" + courierId))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return deserializeResponse(response.body(), Courier.class);

        } catch (Exception e) {
            throw new CustomException("Failed to execute getCourier request", 500, "REQUEST_ERROR", e);
        }
    }

    public Courier executeAddCourier(Courier courier) throws CustomException {
        try {
            String requestBody = serializeRequest(courier);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/addCourier"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return deserializeResponse(response.body(), Courier.class);

        } catch (Exception e) {
            throw new CustomException("Failed to execute addCourier request", 500, "REQUEST_ERROR", e);
        }
    }

    private void validateResponse(HttpResponse<String> response) throws CustomException {
        if (response.statusCode() >= 400) {
            ErrorCode errorCode = ErrorCode.fromStatusCode(response.statusCode());
            throw new CustomException(
                    "Unexpected response status: " + response.statusCode(),
                    response.statusCode(),
                    errorCode.getErrorCode()
            );
        }
    }

    private <T> T deserializeResponse(String responseBody, Class<T> type) throws CustomException {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().readValue(responseBody, type);
        } catch (Exception e) {
            throw new CustomException("Failed to deserialize response", 500, "DESERIALIZATION_ERROR", e);
        }
    }

    private String serializeRequest(Object requestBody) throws CustomException {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(requestBody);
        } catch (Exception e) {
            throw new CustomException("Failed to serialize request", 500, "SERIALIZATION_ERROR", e);
        }
    }
}
