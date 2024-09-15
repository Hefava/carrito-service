package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.domain.spi.IArticuloCarritoPersistencePort;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import com.bootcamp.carrito_service.ports.persistency.mysql.mapper.ArticuloCarritoEntityMapper;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.IArticuloCarritoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticuloCarritoAdapter implements IArticuloCarritoPersistencePort {

    private final IArticuloCarritoRepository articuloCarritoRepository;
    private final ArticuloCarritoEntityMapper articuloCarritoEntityMapper;

    @Override
    @Transactional
    public void agregarArticuloACarrito(ArticuloCarrito articuloCarrito) {
        ArticuloCarritoEntity entity = articuloCarritoEntityMapper.toEntity(articuloCarrito);
        articuloCarritoRepository.save(entity);
    }
}