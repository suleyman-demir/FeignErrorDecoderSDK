package com.sdkcourier.feignErrorDecoderSdk.config;


import com.sdkcourier.feignErrorDecoderSdk.decoder.FeignCustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }
}
