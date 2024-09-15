package com.bootcamp.carrito_service.ports.persistency.mysql.mapper;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticuloCarritoEntityMapper {
    @Mapping(source = "carritoId", target = "carrito.carritoID")
    ArticuloCarritoEntity toEntity(ArticuloCarrito articuloCarrito);
}