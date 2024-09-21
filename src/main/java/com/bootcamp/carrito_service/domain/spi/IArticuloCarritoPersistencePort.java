package com.bootcamp.carrito_service.domain.spi;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;

import java.util.List;

public interface IArticuloCarritoPersistencePort {
    void agregarArticuloACarrito(ArticuloCarrito articuloCarrito);
    void eliminarArticuloDeCarrito(Long carritoID, Long articuloID);
    List<ArticuloCarrito> obtenerArticulosPorCarrito(Long carritoId);
    ArticuloCarrito obtenerArticuloEnCarrito(Long carritoID, Long articuloID);
}
