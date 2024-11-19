package com.sdkcourier.feignErrorDecoderSdk.sdk;

import com.sdkcourier.feignErrorDecoderSdk.client.CourierServiceClient;
import com.sdkcourier.feignErrorDecoderSdk.exception.CustomException;
import com.sdkcourier.feignErrorDecoderSdk.model.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Courier Service SDK for synchronous and asynchronous operations.
 */
@Service
public class CourierServiceSDK {

    private final CourierServiceClient courierServiceClient;

    @Autowired
    public CourierServiceSDK(CourierServiceClient courierServiceClient) {
        this.courierServiceClient = courierServiceClient;
    }

    /**
     * Asynchronously fetches a courier by its ID.
     *
     * @param courierId The ID of the courier to fetch.
     * @return CompletableFuture wrapping the Courier object.
     */
    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.getCourier(courierId);
            } catch (CustomException e) {
                throw new RuntimeException("Async getCourier failed: " + e.getMessage(), e);
            }
        });
    }

    /**
     * Synchronously fetches a courier by its ID.
     *
     * @param courierId The ID of the courier to fetch.
     * @return The fetched Courier object.
     * @throws CustomException If an error occurs during the operation.
     */
    public Courier executeGetCourier(Long courierId) throws CustomException {
        return courierServiceClient.getCourier(courierId);
    }

    /**
     * Synchronously adds a new courier.
     *
     * @param courier The Courier object to add.
     * @return The added Courier object.
     * @throws CustomException If an error occurs during the operation.
     */
    public Courier executeAddCourier(Courier courier) throws CustomException {
        return courierServiceClient.addCourier(courier);
    }

    /**
     * Asynchronously adds a new courier.
     *
     * @param courier The Courier object to add.
     * @return CompletableFuture wrapping the added Courier object.
     */
    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.addCourier(courier);
            } catch (CustomException e) {
                throw new RuntimeException("Async addCourier failed: " + e.getMessage(), e);
            }
        });
    }
}
