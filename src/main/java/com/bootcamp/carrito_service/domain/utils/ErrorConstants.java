package com.bootcamp.carrito_service.domain.utils;

public class ErrorConstants {
    private ErrorConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String ERROR_EN_SOLICITUD = "Error en la solicitud: ";
    public static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado: ";
    public static final String ERROR_INTERNO_SERVIDOR = "Error interno del servidor";
    public static final String ERROR_DESCONOCIDO = "Error desconocido al leer la respuesta";
    public static final String SERVER_ERROR = "El servicio no está disponible, intente más tarde.";
    public static final String ERROR_CANTIDAD = "La cantidad debe ser mayor que 0.";
    public static final String TOKEN_INVALIDO = "Token inválido o usuario no encontrado";
    public static final String MESSAGE = "Message";
}
