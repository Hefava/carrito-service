package com.bootcamp.carrito_service.domain.exception;

public class StockInsuficienteException extends RuntimeException {
    private final String fechaAbastecimiento;
    private final Long articuloID;

    public StockInsuficienteException(Long articuloID, String fechaAbastecimiento) {
        super();
        this.articuloID = articuloID;
        this.fechaAbastecimiento = fechaAbastecimiento;
    }

    public String getFechaAbastecimiento() {
        return fechaAbastecimiento;
    }

    public Long getArticuloID() {
        return articuloID;
    }
}
