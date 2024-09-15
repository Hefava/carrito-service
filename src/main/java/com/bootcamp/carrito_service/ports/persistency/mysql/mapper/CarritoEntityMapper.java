package com.bootcamp.carrito_service.ports.persistency.mysql.mapper;

import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.CarritoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarritoEntityMapper {
    CarritoEntity toEntity(Carrito carrito);
    Carrito toDomain(CarritoEntity carritoEntity);
}
