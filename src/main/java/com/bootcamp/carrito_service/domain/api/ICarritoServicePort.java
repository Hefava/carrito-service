package com.bootcamp.carrito_service.domain.api;

import com.bootcamp.carrito_service.domain.utils.ArticuloRequest;
import com.bootcamp.carrito_service.domain.utils.ArticuloCarritoInfoResponse;

public interface ICarritoServicePort {
    void agregarArticulo(Long articuloID, Long cantidad);
    void eliminarArticulo(Long articuloID, Long carritoID);
    ArticuloCarritoInfoResponse obtenerArticulosConPrecioTotal(ArticuloRequest request);
}
