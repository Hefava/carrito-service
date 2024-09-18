package com.bootcamp.carrito_service.domain.exception;

public class StockInsuficienteException extends RuntimeException {
    private final String fechaAbastecimiento;

    public StockInsuficienteException(String fechaAbastecimiento) {
        super();
        this.fechaAbastecimiento = fechaAbastecimiento;
    }

    public String getFechaAbastecimiento() {
        return fechaAbastecimiento;
    }
}