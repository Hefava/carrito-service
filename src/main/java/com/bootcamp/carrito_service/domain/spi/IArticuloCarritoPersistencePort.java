package com.bootcamp.carrito_service.domain.spi;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;

public interface IArticuloCarritoPersistencePort {
    void agregarArticuloACarrito(ArticuloCarrito articuloCarrito);
}
