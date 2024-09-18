package com.bootcamp.carrito_service.ports.feign;

import com.bootcamp.carrito_service.ports.application.http.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "usuario-service", url = "http://localhost:8080", configuration = FeingClientConfiguration.class)
public interface UsuarioFeign {

    @PostMapping("/auth/validate-token/{token}")
    UsuarioResponse validateToken(@PathVariable String token);
}