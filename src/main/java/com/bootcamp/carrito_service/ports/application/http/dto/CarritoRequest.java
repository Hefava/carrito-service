package com.bootcamp.carrito_service.ports.application.http.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarritoRequest {
    @NotNull
    private Long articuloID;

    @NotNull
    private Long cantidad;
}
