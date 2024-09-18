package com.bootcamp.carrito_service.domain.spi;

import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;

public interface IArticuloPersistencePort {
    ArticuloInfo verificarInfoArticulo(Long articuloID);
}
