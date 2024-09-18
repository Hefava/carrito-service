package com.bootcamp.carrito_service.domain.usecase;

import com.bootcamp.carrito_service.domain.api.usecase.CarritoUseCase;
import com.bootcamp.carrito_service.domain.exception.StockInsuficienteException;
import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.domain.spi.*;
import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarritoUseCaseTest {

    @Mock
    private IArticuloPersistencePort articuloPersistencePort;

    @Mock
    private ICarritoPersistencePort carritoPersistencePort;

    @Mock
    private IUsuarioPersistencePort usuarioPersistencePort;

    @Mock
    private IArticuloCarritoPersistencePort articuloCarritoPersistencePort;

    @Mock
    private ISuministroPersistencePort suministroPersistencePort;

    @InjectMocks
    private CarritoUseCase carritoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgregarArticulo_Success() {
        // Arrange
        Long articuloID = 1L;
        Long cantidad = 2L;
        Long usuarioID = 100L;
        Long carritoID = 200L;

        Carrito carrito = new Carrito();
        carrito.setCarritoID(carritoID);

        ArticuloInfo articuloInfo = new ArticuloInfo(10L, Arrays.asList(1L, 2L));

        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioID);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioID)).thenReturn(carrito);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoID, articuloID)).thenReturn(null);

        // Act
        carritoUseCase.agregarArticulo(articuloID, cantidad);

        // Assert
        verify(articuloCarritoPersistencePort).agregarArticuloACarrito(any(ArticuloCarrito.class));
        verify(carritoPersistencePort).actualizarCarrito(carrito);
    }

    @Test
    void testAgregarArticulo_ArticuloExistente() {
        // Arrange
        Long articuloID = 1L;
        Long cantidad = 5L;
        Long usuarioID = 100L;
        Long carritoID = 200L;

        Carrito carrito = new Carrito();
        carrito.setCarritoID(carritoID);

        ArticuloInfo articuloInfo = new ArticuloInfo(10L, Arrays.asList(1L));
        ArticuloCarrito articuloExistente = new ArticuloCarrito(carritoID, articuloID, 3L);

        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioID);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioID)).thenReturn(carrito);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoID, articuloID)).thenReturn(articuloExistente);

        // Act
        carritoUseCase.agregarArticulo(articuloID, cantidad);

        // Assert
        verify(articuloCarritoPersistencePort).agregarArticuloACarrito(articuloExistente);
        assertEquals(5L, articuloExistente.getCantidad());
    }

    @Test
    void testAgregarArticulo_StockInsuficiente() {
        // Arrange
        Long articuloID = 1L;
        Long cantidad = 20L;
        Long usuarioID = 100L;
        Long carritoID = 200L;

        Carrito carrito = new Carrito();
        carrito.setCarritoID(carritoID);

        ArticuloInfo articuloInfo = new ArticuloInfo(5L, Arrays.asList(1L));

        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioID);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioID)).thenReturn(carrito);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(suministroPersistencePort.getFechaAbastecimiento()).thenReturn("10-12-2024");

        // Act & Assert
        assertThrows(StockInsuficienteException.class, () -> carritoUseCase.agregarArticulo(articuloID, cantidad));
    }

    @Test
    void testAgregarArticulo_CarritoActualizado() {
        // Arrange
        Long articuloID = 1L;
        Long cantidad = 3L;
        Long usuarioID = 100L;
        Long carritoID = 200L;

        Carrito carrito = new Carrito();
        carrito.setCarritoID(carritoID);

        ArticuloInfo articuloInfo = new ArticuloInfo(10L, Arrays.asList(1L));

        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioID);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioID)).thenReturn(carrito);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoID, articuloID)).thenReturn(null);

        // Act
        carritoUseCase.agregarArticulo(articuloID, cantidad);

        // Assert
        verify(carritoPersistencePort).actualizarCarrito(carrito);
    }
}
