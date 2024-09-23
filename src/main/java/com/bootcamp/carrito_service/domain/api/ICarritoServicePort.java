package com.bootcamp.carrito_service.domain.api;

import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoResponseWrapper;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;

public interface ICarritoServicePort {
    void agregarArticulo(Long articuloID, Long cantidad);
    void eliminarArticulo(Long articuloID, Long carritoID);
    ArticuloCarritoInfoResponseWrapper obtenerArticulosConPrecioTotal(ArticuloCarritoInfoRequest request);
}