package com.bootcamp.carrito_service.ports.application.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloCarritoInfoResponseWrapper {
    private List<ArticuloCarritoInfoResponse> content;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalCount;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArticuloCarritoInfoResponse {
        private Long articuloID;
        private String nombre;
        private Long cantidad;
        private double precio;
        private String marcaNombre;
        private List<String> categorias;
    }
}