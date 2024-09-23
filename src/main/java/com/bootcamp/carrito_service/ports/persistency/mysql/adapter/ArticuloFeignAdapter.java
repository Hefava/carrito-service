package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoResponseWrapper;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloInfoResponse;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.IArticuloFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticuloFeignAdapter implements IArticuloPersistencePort {

    private final IArticuloFeign articuloFeign;

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
    public ArticuloCarritoInfoResponseWrapper getArticulosByIds(ArticuloCarritoInfoRequest request) {
        ArticuloCarritoInfoResponseWrapper response = articuloFeign.getArticulosByIds(request);
        return response;
    }
}
