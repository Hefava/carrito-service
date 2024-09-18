package com.bootcamp.carrito_service.ports.persistency.mysql.repository;

import com.bootcamp.carrito_service.ports.feign.FeingClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "transaccion-service", url = "http://localhost:9000", configuration = FeingClientConfiguration.class)
public interface ISuministroFeign {
    @GetMapping("/suministro/fecha-abastecimiento")
    String getFechaAbastecimiento();
}