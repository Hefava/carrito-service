package com.bootcamp.carrito_service.ports.persistency.mysql.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ArticuloCarritoKey implements Serializable {

    private Long carritoId;
    private Long articuloId;
}