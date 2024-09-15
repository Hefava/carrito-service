package com.bootcamp.carrito_service.ports.application.http.controller;

import com.bootcamp.carrito_service.domain.api.ICarritoServicePort;
import com.bootcamp.carrito_service.domain.utils.TokenHolder;
import com.bootcamp.carrito_service.ports.application.http.dto.CarritoRequest;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoRestController {

    private final ICarritoServicePort carritoService;

    @PostMapping("/agregar-articulos")
    public ResponseEntity<Void>  agregarArticulos(
            @RequestHeader("Authorization") String token,
            @RequestBody @Parameter(description = "CarritoRequest", required = true) CarritoRequest carritoRequest) {

        TokenHolder.setToken(token);
        carritoService.agregarArticulo(carritoRequest.getArticuloID(), carritoRequest.getCantidad());
        TokenHolder.clear();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
