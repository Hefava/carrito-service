package com.bootcamp.carrito_service.ports.persistency.mysql;

import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.ports.persistency.mysql.adapter.CarritoAdapter;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.CarritoEntity;
import com.bootcamp.carrito_service.ports.persistency.mysql.mapper.CarritoEntityMapper;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.ICarritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CarritoAdapterTest {

    @Mock
    private ICarritoRepository carritoRepository;

    @Mock
    private CarritoEntityMapper carritoEntityMapper;

    @InjectMocks
    private CarritoAdapter carritoAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerOCrearCarrito_Existe() {
        // Arrange
        Long usuarioID = 1L;
        CarritoEntity carritoEntity = new CarritoEntity();
        Carrito carrito = new Carrito();
        when(carritoRepository.findByUsuarioID(usuarioID)).thenReturn(Optional.of(carritoEntity));
        when(carritoEntityMapper.toDomain(carritoEntity)).thenReturn(carrito);

        // Act
        Carrito result = carritoAdapter.obtenerOCrearCarrito(usuarioID);

        // Assert
        verify(carritoRepository).findByUsuarioID(usuarioID);
        verify(carritoEntityMapper).toDomain(carritoEntity);
        assert result.equals(carrito);
    }

    @Test
    void testActualizarCarrito() {
        // Arrange
        Carrito carrito = new Carrito();
        carrito.setFechaCreacion(LocalDateTime.now().minusDays(1));
        carrito.setFechaActualizacion(LocalDateTime.now().minusDays(1));
        CarritoEntity carritoEntity = new CarritoEntity();
        when(carritoEntityMapper.toEntity(carrito)).thenReturn(carritoEntity);

        // Act
        carritoAdapter.actualizarCarrito(carrito);

        // Assert
        verify(carritoRepository).save(carritoEntity);
        assert carrito.getFechaActualizacion() != null;
    }
}