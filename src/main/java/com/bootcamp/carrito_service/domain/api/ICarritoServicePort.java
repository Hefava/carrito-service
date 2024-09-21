package com.bootcamp.carrito_service.domain.api;

public interface ICarritoServicePort {
    void agregarArticulo(Long articuloID, Long cantidad);
    void eliminarArticulo(Long articuloID, Long carritoID);
}
