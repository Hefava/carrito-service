package com.bootcamp.carrito_service.domain.spi;

import com.bootcamp.carrito_service.domain.utils.ArticuloCarritoInfoResponse;
import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;
import com.bootcamp.carrito_service.domain.utils.ArticuloRequest;

public interface IArticuloPersistencePort {
    ArticuloInfo verificarInfoArticulo(Long articuloID);
    ArticuloCarritoInfoResponse getArticulosByIds(ArticuloRequest request);
}
