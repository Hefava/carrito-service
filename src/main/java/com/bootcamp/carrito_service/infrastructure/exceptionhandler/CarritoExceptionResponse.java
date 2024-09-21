package com.bootcamp.carrito_service.infrastructure.exceptionhandler;

public enum CarritoExceptionResponse {
    STOCK_INSUFICIENTE("No hay suficiente stock para este artículo. "),
    MAXIMO_ARTICULOS_POR_CATEGORIA("Ya has alcanzado el número máximo de artículos permitidos en esta categoría."),
    ARTICULO_NO_ENCONTRADO("El artículo no se encuentra en el carrito.");

    private final String message;

    CarritoExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
