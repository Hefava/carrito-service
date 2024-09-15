package com.bootcamp.carrito_service.ports.persistency.mysql.repository;

import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticuloCarritoRepository extends JpaRepository<ArticuloCarritoEntity, ArticuloCarritoKey> {
}