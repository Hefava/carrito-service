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
public class WrapperStock {
    private double precioTotal;
    private List<ArticuloStockResponse> content;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalCount;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArticuloStockResponse {
        private Long articuloID;
        private String nombre;
        private Long cantidad;
        private Long cantidadEnCarrito;
        private String mensajeAbastecimiento;
        private double precio;
        private String marcaNombre;
        private List<String> categorias;
    }
}