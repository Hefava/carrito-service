package com.bootcamp.carrito_service.domain.utils;

public class CarritoConstants {

    private CarritoConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final Long INICIAL_CATEGORIA_CONTADOR = 0L;
    public static final Long INCREMENTO_CATEGORIA_CONTADOR = 1L;
    public static final int MAXIMO_ARTICULOS_POR_CATEGORIA = 3;
    public static final long MIN_CANTIDAD = 1L;
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String ELIMINAR_ARTICULO_QUERY = "DELETE FROM articulos_carrito WHERE carrito_id = :carritoID AND articulo_id = :articuloID";
    public static final String PARAM_CARRITO = "carritoID";
    public static final String PARAM_ARTICULO = "articuloID";
}
