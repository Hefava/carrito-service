package com.bootcamp.carrito_service.ports.application.http.mapper;

import com.bootcamp.carrito_service.domain.utils.ArticuloRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticuloRequestMapper {

    ArticuloRequestMapper INSTANCE = Mappers.getMapper(ArticuloRequestMapper.class);

    @Mapping(source = "pageable.pageNumber", target = "pageable.pageNumber")
    @Mapping(source = "pageable.pageSize", target = "pageable.pageSize")
    ArticuloRequest toDomain(ArticuloCarritoInfoRequest request);

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
