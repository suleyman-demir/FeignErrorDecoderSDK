package com.sdkcourier.feignErrorDecoderSdk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${baseUrl}")
    private String baseUrl;

}

