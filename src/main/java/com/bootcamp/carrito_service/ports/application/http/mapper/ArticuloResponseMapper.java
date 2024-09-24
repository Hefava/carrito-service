package com.bootcamp.carrito_service.ports.application.http.mapper;

import com.bootcamp.carrito_service.domain.utils.ArticuloCarritoInfoResponse;
import com.bootcamp.carrito_service.domain.utils.ArticuloCarritoInfo;
import com.bootcamp.carrito_service.ports.application.http.dto.WrapperStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticuloResponseMapper {

    // Mapea de dominio a DTO
    @Mapping(source = "articulos", target = "content") // Mapea la lista de artículos del dominio al DTO
    WrapperStock toDto(ArticuloCarritoInfoResponse domainResponse);

    // Mapea de DTO a dominio
    @Mapping(source = "content", target = "articulos") // Mapea la lista de artículos del DTO al dominio
    ArticuloCarritoInfoResponse toDomain(WrapperStock dtoResponse);

    // Mapeo individual para ArticuloCarritoInfo -> ArticuloCarritoInfoResponse del DTO
    @Mapping(source = "articuloID", target = "articuloID")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "categorias", target = "categorias")
    WrapperStock.ArticuloStockResponse toDtoArticulo(ArticuloCarritoInfo articulo);

    // Mapeo individual para ArticuloCarritoInfoResponse del DTO -> ArticuloCarritoInfo
    @Mapping(source = "articuloID", target = "articuloID")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "categorias", target = "categorias")
    ArticuloCarritoInfo toDomainArticulo(WrapperStock.ArticuloStockResponse articuloDto);

    // Mapea la lista de artículos del dominio a DTO
    List<WrapperStock.ArticuloStockResponse> toDtoArticulos(List<ArticuloCarritoInfo> articulos);

    // Mapea la lista de artículos del DTO al dominio
    List<ArticuloCarritoInfo> toDomainArticulos(List<WrapperStock.ArticuloStockResponse> articulosDto);
}
