package com.bootcamp.carrito_service.ports.feign;

import com.bootcamp.carrito_service.domain.utils.TokenHolder;
import com.bootcamp.carrito_service.infrastructure.exceptionhandler.FeignErrorDecoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.bootcamp.carrito_service.domain.utils.SecurityConstants.AUTHORIZATION;

@Configuration
public class FeingClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = TokenHolder.getToken();
            if (token != null && !token.isEmpty()) {
                requestTemplate.header(AUTHORIZATION, token);
            }
        };
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }
}

