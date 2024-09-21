package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.domain.spi.ICarritoPersistencePort;
import com.bootcamp.carrito_service.ports.persistency.mysql.mapper.CarritoEntityMapper;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.ICarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CarritoAdapter implements ICarritoPersistencePort {
    private final ICarritoRepository carritoRepository;
    private final CarritoEntityMapper carritoEntityMapper;

    @Override
    public Carrito obtenerOCrearCarrito(Long usuarioID) {
        return carritoRepository.findByUsuarioID(usuarioID)
                .map(carritoEntityMapper::toDomain)
                .orElseGet(() -> {
                    Carrito carrito = new Carrito();
                    carrito.setUsuarioID(usuarioID);
                    carrito.setFechaCreacion(LocalDateTime.now());
                    carrito.setFechaActualizacion(LocalDateTime.now());
                    return carritoEntityMapper.toDomain(
                            carritoRepository.save(carritoEntityMapper.toEntity(carrito))
                    );
                });
    }
}