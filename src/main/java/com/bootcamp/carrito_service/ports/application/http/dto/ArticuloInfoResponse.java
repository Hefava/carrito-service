package com.bootcamp.carrito_service.ports.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloInfoResponse {
    private Long cantidad;
    private List<CategoriaInfoDto> categorias;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriaInfoDto {
        private Long categoriaID;
    }
}