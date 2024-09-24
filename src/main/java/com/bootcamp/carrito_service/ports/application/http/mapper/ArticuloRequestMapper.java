package com.bootcamp.carrito_service.ports.application.http.mapper;

import com.bootcamp.carrito_service.domain.utils.ArticuloRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticuloRequestMapper {

    // Mapeo de ArticuloCarritoInfoRequest a ArticuloRequest
    @Mapping(source = "pageable.pageNumber", target = "pageable.pageNumber")
    @Mapping(source = "pageable.pageSize", target = "pageable.pageSize")
    ArticuloRequest toDomain(ArticuloCarritoInfoRequest request);

    // Mapeo de ArticuloRequest a ArticuloCarritoInfoRequest
    @Mapping(source = "articuloIds", target = "articuloIds")
    @Mapping(source = "sortBy", target = "sortBy")
    @Mapping(source = "order", target = "order")
    @Mapping(source = "categoriaNombre", target = "categoriaNombre")
    @Mapping(source = "marcaNombre", target = "marcaNombre")
    @Mapping(source = "pageable", target = "pageable")
    ArticuloCarritoInfoRequest toDto(ArticuloRequest request);

    default ArticuloRequest.Pageable toDomain(ArticuloCarritoInfoRequest.PageableRequest pageableRequest) {
        if (pageableRequest == null) {
            return null;
        }
        return new ArticuloRequest.Pageable(
                pageableRequest.getPageNumber(),
                pageableRequest.getPageSize()
        );
    }
}