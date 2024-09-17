package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.domain.spi.IArticuloCarritoPersistencePort;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import com.bootcamp.carrito_service.ports.persistency.mysql.mapper.ArticuloCarritoEntityMapper;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.IArticuloCarritoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    @Transactional
    public List<ArticuloCarrito> obtenerArticulosPorCarrito(Long carritoId) {
        List<ArticuloCarritoEntity> articuloCarritoEntities = articuloCarritoRepository.findByCarritoId(carritoId);
        return articuloCarritoEntities.stream()
                .map(articuloCarritoEntityMapper::toDomain)
                .toList();
    }

    @Override
    public ArticuloCarrito obtenerArticuloEnCarrito(Long carritoID, Long articuloID) {
        ArticuloCarritoEntity entity = articuloCarritoRepository.findByCarritoIdAndArticuloId(carritoID, articuloID);
        return entity != null ? articuloCarritoEntityMapper.toDomain(entity) : null;
    }
}