package com.bootcamp.carrito_service.ports.persistency.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "carritos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carritoID;

    @Column(name = "usuarioID", nullable = false)
    private Long usuarioID;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

}
