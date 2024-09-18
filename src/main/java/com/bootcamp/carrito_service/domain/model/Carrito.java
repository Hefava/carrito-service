package com.bootcamp.carrito_service.domain.model;

import java.time.LocalDateTime;

public class Carrito {
    private Long carritoID;
    private Long usuarioID;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Carrito() {
    }

    public Carrito(Long carritoID, Long usuarioID, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.carritoID = carritoID;
        this.usuarioID = usuarioID;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getCarritoID() {
        return carritoID;
    }

    public void setCarritoID(Long carritoID) {
        this.carritoID = carritoID;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}