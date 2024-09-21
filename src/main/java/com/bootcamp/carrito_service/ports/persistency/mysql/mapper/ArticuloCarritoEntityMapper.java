package com.bootcamp.carrito_service.ports.persistency.mysql.mapper;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticuloCarritoEntityMapper {
    ArticuloCarritoEntity toEntity(ArticuloCarrito articuloCarrito);
    ArticuloCarrito toDomain(ArticuloCarritoEntity articuloCarritoEntity);
}