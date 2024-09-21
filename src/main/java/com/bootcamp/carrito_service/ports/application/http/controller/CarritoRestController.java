package com.bootcamp.carrito_service.ports.application.http.controller;

import com.bootcamp.carrito_service.domain.api.ICarritoServicePort;
import com.bootcamp.carrito_service.domain.utils.TokenHolder;
import com.bootcamp.carrito_service.ports.application.http.dto.AgregarArticuloACarritoRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.EliminarArticuloCarritoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoRestController {

    private final ICarritoServicePort carritoService;

    @Operation(summary = "Agregar artículos al carrito", description = "Permite agregar artículos al carrito especificado en la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículos agregados exitosamente al carrito."),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Los datos del carrito pueden ser inválidos o incompletos."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor. Se produjo un problema al procesar la solicitud.")
    })
    @PostMapping("/agregar-articulos")
    public ResponseEntity<Void> agregarArticulos(
            @RequestHeader("Authorization") @Parameter(required = true) String token,
            @RequestBody @Valid @Parameter(required = true) AgregarArticuloACarritoRequest carritoRequest) {

        TokenHolder.setToken(token);
        carritoService.agregarArticulo(carritoRequest.getArticuloID(), carritoRequest.getCantidad());
        TokenHolder.clear();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Eliminar artículos del carrito", description = "Permite eliminar artículos del carrito especificado en la solicitud.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Artículos eliminados exitosamente del carrito."),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Los datos del carrito pueden ser inválidos o incompletos."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor. Se produjo un problema al procesar la solicitud.")
    })
    @DeleteMapping("/eliminar-articulo-de-carrito")
    public ResponseEntity<Void> eliminarArticuloDeCarrito(
            @RequestBody @Valid @Parameter(description = "Identificadores del carrito y del artículo a eliminar.", required = true) EliminarArticuloCarritoRequest eliminarArticuloCarritoRequest) {
        carritoService.eliminarArticulo(eliminarArticuloCarritoRequest.getCarritoID(), eliminarArticuloCarritoRequest.getArticuloID());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}