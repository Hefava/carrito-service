package com.bootcamp.carrito_service.ports.application.http.mapper;

import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloInfoResponse;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloInfoResponse.CategoriaInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloInfoMapper {

    @Mapping(source = "categorias", target = "categorias", qualifiedByName = "mapCategoriasToIds")
    ArticuloInfo toModel(ArticuloInfoResponse articuloInfoResponse);

    @Named("mapCategoriasToIds")
    default List<Long> mapCategoriasToIds(List<CategoriaInfoDto> categorias) {
        return categorias.stream()
                .map(CategoriaInfoDto::getCategoriaID)
                .toList();
    }
}
