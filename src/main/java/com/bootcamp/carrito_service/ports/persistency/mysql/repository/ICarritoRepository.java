package com.bootcamp.carrito_service.ports.persistency.mysql.repository;

import com.bootcamp.carrito_service.ports.persistency.mysql.entity.CarritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICarritoRepository extends JpaRepository<CarritoEntity, Long> {
    Optional<CarritoEntity> findByUsuarioID(Long usuarioID);
}
