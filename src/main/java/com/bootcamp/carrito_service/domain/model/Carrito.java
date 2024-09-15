package com.bootcamp.carrito_service.domain.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Carrito {
    private Long carritoID;
    private Long usuarioID;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    private Map<Long, Long> articulos;

    public Carrito() {
        this.articulos = new HashMap<>();
    }

    public Carrito(Long carritoID, Long usuarioID, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Map<Long, Long> articulos) {
        this.carritoID = carritoID;
        this.usuarioID = usuarioID;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.articulos = articulos;
    }

    public void agregarArticulo(Long articuloID, Long cantidad) {
        this.articulos.put(articuloID, this.articulos.getOrDefault(articuloID, 0L) + cantidad);
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Map<Long, Long> obtenerContadorPorCategoria(Map<Long, Set<Long>> articuloCategorias) {
        Map<Long, Long> contadorCategorias = new HashMap<>();

        for (Map.Entry<Long, Long> articulo : articulos.entrySet()) {
            Long articuloID = articulo.getKey();
            Set<Long> categoriasArticulo = articuloCategorias.get(articuloID);
            for (Long categoriaID : categoriasArticulo) {
                contadorCategorias.put(categoriaID, contadorCategorias.getOrDefault(categoriaID, 0L) + 1);
            }
        }
        return contadorCategorias;
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

    public Map<Long, Long> getArticulos() {
        return articulos;
    }

    public void setArticulos(Map<Long, Long> articulos) {
        this.articulos = articulos;
    }
}