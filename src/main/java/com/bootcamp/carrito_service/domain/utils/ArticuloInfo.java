package com.bootcamp.carrito_service.domain.utils;

import java.util.List;

public class ArticuloInfo {
    private Long articuloID;
    private Long cantidad;
    private List<Long> categorias;
    private Double precio;

    public ArticuloInfo() {
    }

    public ArticuloInfo(Long articuloID,Long cantidad, List<Long> categorias, Double precio) {
        this.articuloID = articuloID;
        this.cantidad = cantidad;
        this.categorias = categorias;
        this.precio = precio;
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

    public Long getArticuloID() {
        return articuloID;
    }

    public void setArticuloID(Long articuloID) {
        this.articuloID = articuloID;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precioTotal) {
        this.precio = precioTotal;
    }
}
