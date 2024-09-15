package com.bootcamp.carrito_service.domain.model;

public class ArticuloCarrito {
    private Long carritoId;
    private Long articuloId;
    private Long cantidad;

    public ArticuloCarrito() {
    }

    public ArticuloCarrito(Long carritoId, Long articuloId, Long cantidad) {
        this.carritoId = carritoId;
        this.articuloId = articuloId;
        this.cantidad = cantidad;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
