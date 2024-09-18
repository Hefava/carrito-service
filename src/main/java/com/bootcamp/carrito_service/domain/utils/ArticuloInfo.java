package com.bootcamp.carrito_service.domain.utils;

import java.util.List;

public class ArticuloInfo {
    private Long cantidad;
    private List<Long> categorias;

    public ArticuloInfo() {
    }

    public ArticuloInfo(Long cantidad, List<Long> categorias) {
        this.cantidad = cantidad;
        this.categorias = categorias;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public List<Long> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Long> categorias) {
        this.categorias = categorias;
    }
}
