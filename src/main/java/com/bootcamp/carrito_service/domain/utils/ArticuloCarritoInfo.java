package com.bootcamp.carrito_service.domain.utils;

import java.util.List;

public class ArticuloCarritoInfo {
    private Long articuloID;
    private String nombre;
    private Long cantidad;
    private double precio;
    private String marcaNombre;
    private List<String> categorias;

        public ArticuloCarritoInfo(Long articuloID, String nombre, Long cantidad, double precio, String marcaNombre, List<String> categorias) {
        this.articuloID = articuloID;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.marcaNombre = marcaNombre;
        this.categorias = categorias;
    }

    public Long getArticuloID() {
        return articuloID;
    }

    public void setArticuloID(Long articuloID) {
        this.articuloID = articuloID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
}
