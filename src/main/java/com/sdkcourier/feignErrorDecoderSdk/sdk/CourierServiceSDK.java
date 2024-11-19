package com.sdkcourier.feignErrorDecoderSdk.sdk;


import com.sdkcourier.feignErrorDecoderSdk.client.CourierServiceClient;
import com.sdkcourier.feignErrorDecoderSdk.exception.CustomException;
import com.sdkcourier.feignErrorDecoderSdk.model.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * SDK for Courier Service.
 */
@Service
public class CourierServiceSDK {

    private final CourierServiceClient courierServiceClient;

    @Autowired
    public CourierServiceSDK(CourierServiceClient courierServiceClient) {
        this.courierServiceClient = courierServiceClient;
    }

    public Courier executeGetCourier(Long courierId) throws CustomException {
        return courierServiceClient.getCourier(courierId);
    }

    public Courier executeAddCourier(Courier courier) throws CustomException {
        return courierServiceClient.addCourier(courier);
    }

    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> courierServiceClient.getCourier(courierId));
    }

    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {
        return CompletableFuture.supplyAsync(() -> courierServiceClient.addCourier(courier));
    }
}
