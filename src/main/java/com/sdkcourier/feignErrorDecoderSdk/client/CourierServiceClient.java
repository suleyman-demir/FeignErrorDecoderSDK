package com.sdkcourier.feignErrorDecoderSdk.client;


import com.sdkcourier.feignErrorDecoderSdk.exception.FeignErrorDecoder;
import com.sdkcourier.feignErrorDecoderSdk.model.Courier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign Client for Courier Service.
 */
@FeignClient(
        name = "courier-service",
        url = "${courier.service.base-url}",
        configuration = FeignErrorDecoder.class
)
public interface CourierServiceClient {

    @GetMapping("/getCourier/{id}")
    Courier getCourier(@PathVariable("id") Long courierId);

    @PostMapping("/addCourier")
    Courier addCourier(@RequestBody Courier courier);
}
