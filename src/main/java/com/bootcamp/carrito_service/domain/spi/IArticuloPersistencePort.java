package com.bootcamp.carrito_service.domain.spi;

import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoResponseWrapper;

public interface IArticuloPersistencePort {
    ArticuloInfo verificarInfoArticulo(Long articuloID);
    ArticuloCarritoInfoResponseWrapper getArticulosByIds(ArticuloCarritoInfoRequest request);
}
