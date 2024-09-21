package com.bootcamp.carrito_service.ports.persistency.mysql.repository;

import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.bootcamp.carrito_service.domain.utils.CarritoConstants.*;

public interface IArticuloCarritoRepository extends JpaRepository<ArticuloCarritoEntity, ArticuloCarritoKey> {
    List<ArticuloCarritoEntity> findByCarritoId(Long carritoId);
    ArticuloCarritoEntity findByCarritoIdAndArticuloId(Long carritoID, Long articuloID);
    @Modifying
    @Transactional
    @Query(value = ELIMINAR_ARTICULO_QUERY, nativeQuery = true)
    void eliminarArticuloDeCarrito(@Param(PARAM_CARRITO) Long carritoID, @Param(PARAM_ARTICULO) Long articuloID);
}