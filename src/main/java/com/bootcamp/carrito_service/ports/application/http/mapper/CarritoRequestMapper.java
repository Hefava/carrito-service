package com.bootcamp.carrito_service.ports.application.http.mapper;

import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.ports.application.http.dto.CarritoRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarritoRequestMapper {
    Carrito toCarrito(CarritoRequest carritoRequest);
}