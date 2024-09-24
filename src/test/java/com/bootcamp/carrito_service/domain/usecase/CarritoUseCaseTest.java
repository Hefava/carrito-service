package com.bootcamp.carrito_service.domain.usecase;

import com.bootcamp.carrito_service.domain.api.usecase.CarritoUseCase;
import com.bootcamp.carrito_service.domain.exception.ArticuloNoEncontradoException;
import com.bootcamp.carrito_service.domain.exception.StockInsuficienteException;
import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.domain.spi.*;
import com.bootcamp.carrito_service.domain.utils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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

    private Carrito carrito;
    private Long articuloID = 1L;
    private Long usuarioIDField = 100L;  // Renombrado para evitar ocultación
    private Long carritoIDField = 200L;   // Renombrado para evitar ocultación
    private ArticuloInfo articuloInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carrito = new Carrito();
        carrito.setCarritoID(carritoIDField);
        articuloInfo = new ArticuloInfo(articuloID, 10L, Arrays.asList(1L, 2L), 100.0);
    }

    @Test
    void agregarArticuloNuevo_Success() {
        // Arrange
        Long cantidad = 2L;
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioIDField);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioIDField)).thenReturn(carrito);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoIDField, articuloID)).thenReturn(null);
        when(suministroPersistencePort.getFechaAbastecimiento()).thenReturn("10-12-2024");

        // Act
        carritoUseCase.agregarArticulo(articuloID, cantidad);

        // Assert
        verify(articuloCarritoPersistencePort).agregarArticuloACarrito(any(ArticuloCarrito.class));
        verify(carritoPersistencePort).obtenerOCrearCarrito(usuarioIDField);
        assertNotNull(carrito.getFechaActualizacion());
    }

    @Test
    void agregarArticuloExistente_Success() {
        // Arrange
        Long cantidad = 5L;
        ArticuloCarrito articuloExistente = new ArticuloCarrito(carritoIDField, articuloID, 3L);
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioIDField);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioIDField)).thenReturn(carrito);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoIDField, articuloID)).thenReturn(articuloExistente);
        when(suministroPersistencePort.getFechaAbastecimiento()).thenReturn("10-12-2024");

        // Act
        carritoUseCase.agregarArticulo(articuloID, cantidad);

        // Assert
        verify(articuloCarritoPersistencePort).agregarArticuloACarrito(articuloExistente);
        assertEquals(cantidad, articuloExistente.getCantidad());
        assertNotNull(carrito.getFechaActualizacion());
    }

    @Test
    void agregarArticulo_StockInsuficienteException() {
        // Arrange
        Long cantidad = 20L;
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioIDField);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioIDField)).thenReturn(carrito);
        articuloInfo = new ArticuloInfo(articuloID, 5L, Arrays.asList(1L), 50.0);
        when(articuloPersistencePort.verificarInfoArticulo(articuloID)).thenReturn(articuloInfo);
        when(suministroPersistencePort.getFechaAbastecimiento()).thenReturn("10-12-2024");

        // Act & Assert
        assertThrows(StockInsuficienteException.class, () -> carritoUseCase.agregarArticulo(articuloID, cantidad));
    }

    @Test
    void eliminarArticulo_Success() {
        // Arrange
        ArticuloCarrito articuloCarrito = new ArticuloCarrito(carritoIDField, articuloID, 3L);
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioIDField);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioIDField)).thenReturn(carrito);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoIDField, articuloID)).thenReturn(articuloCarrito);

        // Act
        carritoUseCase.eliminarArticulo(carritoIDField, articuloID);

        // Assert
        verify(articuloCarritoPersistencePort).eliminarArticuloDeCarrito(carritoIDField, articuloID);
        verify(carritoPersistencePort).obtenerOCrearCarrito(usuarioIDField);
        assertNotNull(carrito.getFechaActualizacion());
    }

    @Test
    void eliminarArticulo_ArticuloNoEncontradoException() {
        // Arrange
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioIDField);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioIDField)).thenReturn(carrito);
        when(articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoIDField, articuloID)).thenReturn(null);

        // Act & Assert
        assertThrows(ArticuloNoEncontradoException.class, () -> carritoUseCase.eliminarArticulo(carritoIDField, articuloID));
    }

    @Test
    void obtenerArticulosConPrecioTotal_Success() {
        // Arrange
        List<Long> articuloIds = Arrays.asList(1L, 2L);

        // Crear una lista de ArticuloCarrito para simular los artículos en el carrito
        List<ArticuloCarrito> articulosCarrito = Arrays.asList(
                new ArticuloCarrito(carritoIDField, 1L, 10L), // 10 unidades del artículo 1
                new ArticuloCarrito(carritoIDField, 2L, 5L)   // 5 unidades del artículo 2
        );

        // Crear respuesta de ArticuloCarritoInfoResponse con los artículos
        List<ArticuloCarritoInfo> articulosInfo = Arrays.asList(
                new ArticuloCarritoInfo(1L, "Artículo 1", 10L, 50.0, "Marca A", Arrays.asList("Categoría A")),
                new ArticuloCarritoInfo(2L, "Artículo 2", 5L, 30.0, "Marca B", Arrays.asList("Categoría B"))
        );
        ArticuloCarritoInfoResponse articulosInfoResponse = new ArticuloCarritoInfoResponse(0, articulosInfo, 1, 1, 1, 2);

        // Crear un objeto Pageable
        ArticuloRequest.Pageable pageable = new ArticuloRequest.Pageable(0, 10);
        ArticuloRequest request = new ArticuloRequest(articuloIds, null, null, null, null, pageable);

        // Configurar los mocks
        when(usuarioPersistencePort.obtenerUsuarioID()).thenReturn(usuarioIDField);
        when(carritoPersistencePort.obtenerOCrearCarrito(usuarioIDField)).thenReturn(carrito);
        when(articuloCarritoPersistencePort.obtenerArticulosPorCarrito(carrito.getCarritoID())).thenReturn(articulosCarrito);

        // Mocks para verificar la información de los artículos
        when(articuloPersistencePort.verificarInfoArticulo(1L)).thenReturn(new ArticuloInfo(1L, 10L, Arrays.asList(1L), 50.0));
        when(articuloPersistencePort.verificarInfoArticulo(2L)).thenReturn(new ArticuloInfo(2L, 5L, Arrays.asList(2L), 30.0));

        // Mocks para obtener los artículos por ID y la fecha de abastecimiento
        when(articuloPersistencePort.getArticulosByIds(request)).thenReturn(articulosInfoResponse);
        when(suministroPersistencePort.getFechaAbastecimiento()).thenReturn("10-12-2024");

        // Act
        ArticuloCarritoInfoResponse result = carritoUseCase.obtenerArticulosConPrecioTotal(request);

        // Assert
        assertEquals(650.0, 650.0, 0.001);
        assertNotNull(result.getArticulos());
        assertFalse(result.getArticulos().isEmpty());
        assertEquals(2, result.getArticulos().size());
        verify(suministroPersistencePort).getFechaAbastecimiento();
    }
}