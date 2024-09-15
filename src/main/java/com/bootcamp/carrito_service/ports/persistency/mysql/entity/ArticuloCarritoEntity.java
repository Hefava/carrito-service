package com.bootcamp.carrito_service.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articulos_carrito")
@Getter
@Setter
@NoArgsConstructor
@IdClass(ArticuloCarritoKey.class)
public class ArticuloCarritoEntity {

    @Id
    @Column(name = "carrito_id")
    private Long carritoId;

    @Id
    @Column(name = "articulo_id")
    private Long articuloId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id", insertable = false, updatable = false)
    private CarritoEntity carrito;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    public ArticuloCarritoEntity(CarritoEntity carrito, Long articuloId, Long cantidad) {
        this.carritoId = carrito.getCarritoID();
        this.articuloId = articuloId;
        this.carrito = carrito;
        this.cantidad = cantidad;
    }
}