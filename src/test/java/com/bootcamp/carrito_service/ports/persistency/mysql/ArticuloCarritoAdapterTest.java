package com.bootcamp.carrito_service.ports.persistency.mysql;

import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.ports.persistency.mysql.adapter.ArticuloCarritoAdapter;
import com.bootcamp.carrito_service.ports.persistency.mysql.entity.ArticuloCarritoEntity;
import com.bootcamp.carrito_service.ports.persistency.mysql.mapper.ArticuloCarritoEntityMapper;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.IArticuloCarritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ArticuloCarritoAdapterTest {

    @Mock
    private IArticuloCarritoRepository articuloCarritoRepository;

    @Mock
    private ArticuloCarritoEntityMapper articuloCarritoEntityMapper;

    @InjectMocks
    private ArticuloCarritoAdapter articuloCarritoAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgregarArticuloACarrito() {
        // Arrange
        ArticuloCarrito articuloCarrito = new ArticuloCarrito();
        ArticuloCarritoEntity articuloCarritoEntity = new ArticuloCarritoEntity();
        when(articuloCarritoEntityMapper.toEntity(articuloCarrito)).thenReturn(articuloCarritoEntity);

        // Act
        articuloCarritoAdapter.agregarArticuloACarrito(articuloCarrito);

        // Assert
        verify(articuloCarritoRepository).save(articuloCarritoEntity);
    }

    @Test
    void testObtenerArticulosPorCarrito() {
        // Arrange
        Long carritoId = 1L;
        ArticuloCarritoEntity articuloCarritoEntity = new ArticuloCarritoEntity();
        ArticuloCarrito articuloCarrito = new ArticuloCarrito();
        when(articuloCarritoRepository.findByCarritoId(carritoId))
                .thenReturn(Collections.singletonList(articuloCarritoEntity));
        when(articuloCarritoEntityMapper.toDomain(articuloCarritoEntity)).thenReturn(articuloCarrito);

        // Act
        List<ArticuloCarrito> result = articuloCarritoAdapter.obtenerArticulosPorCarrito(carritoId);

        // Assert
        verify(articuloCarritoRepository).findByCarritoId(carritoId);
        verify(articuloCarritoEntityMapper).toDomain(articuloCarritoEntity);
        assert result.size() == 1;
        assert result.get(0).equals(articuloCarrito);
    }

    @Test
    void testObtenerArticuloEnCarrito_Encontrado() {
        // Arrange
        Long carritoId = 1L;
        Long articuloId = 1L;
        ArticuloCarritoEntity articuloCarritoEntity = new ArticuloCarritoEntity();
        ArticuloCarrito articuloCarrito = new ArticuloCarrito();
        when(articuloCarritoRepository.findByCarritoIdAndArticuloId(carritoId, articuloId))
                .thenReturn(articuloCarritoEntity);
        when(articuloCarritoEntityMapper.toDomain(articuloCarritoEntity)).thenReturn(articuloCarrito);

        // Act
        ArticuloCarrito result = articuloCarritoAdapter.obtenerArticuloEnCarrito(carritoId, articuloId);

        // Assert
        verify(articuloCarritoRepository).findByCarritoIdAndArticuloId(carritoId, articuloId);
        verify(articuloCarritoEntityMapper).toDomain(articuloCarritoEntity);
        assert result.equals(articuloCarrito);
    }

    @Test
    void testObtenerArticuloEnCarrito_NoEncontrado() {
        // Arrange
        Long carritoId = 1L;
        Long articuloId = 1L;
        when(articuloCarritoRepository.findByCarritoIdAndArticuloId(carritoId, articuloId)).thenReturn(null);

        // Act
        ArticuloCarrito result = articuloCarritoAdapter.obtenerArticuloEnCarrito(carritoId, articuloId);

        // Assert
        verify(articuloCarritoRepository).findByCarritoIdAndArticuloId(carritoId, articuloId);
        assert result == null;
    }
}