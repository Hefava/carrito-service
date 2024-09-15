package com.bootcamp.carrito_service.domain.exception;

public class MaximoArticulosPorCategoriaException extends RuntimeException {
    public MaximoArticulosPorCategoriaException(String message) {
        super(message);
    }
}
