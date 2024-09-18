package com.bootcamp.carrito_service.infrastructure.exceptionhandler;

public enum StatusConstants {
    BAD_REQUEST(400, "Error en la solicitud"),
    NOT_FOUND(404, "Recurso no encontrado"),
    INTERNAL_SERVER_ERROR(500, "Error interno del servidor"),
    UNKNOWN_ERROR(0, "Error desconocido");

    private final int code;
    private final String message;

    StatusConstants(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}