package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.carrito_service.domain.utils.*;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoResponseWrapper;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloInfoResponse;
import com.bootcamp.carrito_service.ports.application.http.mapper.ArticuloRequestMapper;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.IArticuloFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticuloFeignAdapter implements IArticuloPersistencePort {

    private final IArticuloFeign articuloFeign;
    private final ArticuloRequestMapper articuloRequestMapper;

    @Override
    public ArticuloInfo verificarInfoArticulo(Long articuloID) {
        ArticuloInfoResponse response = articuloFeign.getArticuloInfo(articuloID);

        ArticuloInfo articuloInfo = new ArticuloInfo();
        articuloInfo.setCantidad(response.getCantidad());
        articuloInfo.setCategorias(
                response.getCategorias().stream()
                        .map(c -> c.getCategoriaID())
                        .toList()
        );
        return articuloInfo;
    }

    @Override
    public ArticuloCarritoInfoResponse getArticulosByIds(ArticuloRequest request) {
        ArticuloCarritoInfoRequest feignRequest = articuloRequestMapper.toDto(request);

        ArticuloCarritoInfoResponseWrapper responseWrapper = articuloFeign.getArticulosByIds(feignRequest);

        List<ArticuloCarritoInfo> articulos = responseWrapper.getContent().stream()
                .map(this::mapToDomain)
                .toList();

        return new ArticuloCarritoInfoResponse(
                0.0,
                articulos,
                responseWrapper.getPage(),
                responseWrapper.getPageSize(),
                responseWrapper.getTotalPages(),
                responseWrapper.getTotalCount()
        );
    }

    private ArticuloCarritoInfo mapToDomain(ArticuloCarritoInfoResponseWrapper.ArticuloCarritoInfoResponse dto) {
        return new ArticuloCarritoInfo(
                dto.getArticuloID(),
                dto.getNombre(),
                dto.getCantidad(),
                dto.getPrecio(),
                dto.getMarcaNombre(),
                dto.getCategorias()
        );
    }
}
