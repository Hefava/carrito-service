package com.bootcamp.carrito_service.domain.spi;

import com.bootcamp.carrito_service.domain.model.Carrito;

public interface ICarritoPersistencePort {
    Carrito obtenerOCrearCarrito(Long usuarioID);
}
