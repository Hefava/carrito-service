package com.bootcamp.carrito_service.ports.persistency.mysql.repository;

import com.bootcamp.carrito_service.ports.feign.FeingClientConfiguration;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-service", url = "http://localhost:8090", configuration = FeingClientConfiguration.class)
public interface IArticuloFeign {

    @GetMapping("/articulo/articulo-info/{articuloID}")
    ArticuloInfoResponse getArticuloInfo(@PathVariable Long articuloID);
}