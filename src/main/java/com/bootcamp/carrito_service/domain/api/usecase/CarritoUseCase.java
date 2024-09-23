package com.bootcamp.carrito_service.domain.api.usecase;

import com.bootcamp.carrito_service.domain.api.ICarritoServicePort;
import com.bootcamp.carrito_service.domain.exception.ArticuloNoEncontradoException;
import com.bootcamp.carrito_service.domain.exception.MaximoArticulosPorCategoriaException;
import com.bootcamp.carrito_service.domain.exception.StockInsuficienteException;
import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.domain.spi.*;
import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;
import com.bootcamp.carrito_service.domain.utils.CarritoConstants;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoRequest;
import com.bootcamp.carrito_service.ports.application.http.dto.ArticuloCarritoInfoResponseWrapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CarritoUseCase implements ICarritoServicePort {
    private final IArticuloPersistencePort articuloPersistencePort;
    private final ICarritoPersistencePort carritoPersistencePort;
    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IArticuloCarritoPersistencePort articuloCarritoPersistencePort;
    private final ISuministroPersistencePort suministroPersistencePort;

    public CarritoUseCase(IArticuloPersistencePort articuloPersistencePort, ICarritoPersistencePort carritoPersistencePort, IUsuarioPersistencePort usuarioPersistencePort, IArticuloCarritoPersistencePort articuloCarritoPersistencePort, ISuministroPersistencePort suministroPersistencePort) {
        this.articuloPersistencePort = articuloPersistencePort;
        this.carritoPersistencePort = carritoPersistencePort;
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.articuloCarritoPersistencePort = articuloCarritoPersistencePort;
        this.suministroPersistencePort = suministroPersistencePort;
    }

    @Override
    public void agregarArticulo(Long articuloID, Long cantidad) {
        Long usuarioID = usuarioPersistencePort.obtenerUsuarioID();
        Carrito carrito = carritoPersistencePort.obtenerOCrearCarrito(usuarioID);

        ArticuloInfo articuloInfo = verificarStockDisponible(articuloID, cantidad);
        ArticuloCarrito articuloExistente = articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carrito.getCarritoID(), articuloID);

        if (articuloExistente != null) {
            actualizarArticuloExistente(articuloExistente, cantidad, articuloInfo);
        } else {
            agregarNuevoArticulo(carrito, articuloID, cantidad, articuloInfo);
        }

        carrito.setFechaActualizacion(LocalDateTime.now());
    }

    @Override
    public void eliminarArticulo(Long carritoID, Long articuloID) {
        Long usuarioID = usuarioPersistencePort.obtenerUsuarioID();
        Carrito carrito = carritoPersistencePort.obtenerOCrearCarrito(usuarioID);
        ArticuloCarrito articuloCarrito = articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carritoID, articuloID);
        if (articuloCarrito != null) {
            articuloCarritoPersistencePort.eliminarArticuloDeCarrito(carritoID, articuloID);
            carrito.setFechaActualizacion(LocalDateTime.now());
        } else {
            throw new ArticuloNoEncontradoException();
        }
    }

    @Override
    public ArticuloCarritoInfoResponseWrapper obtenerArticulosConPrecioTotal(ArticuloCarritoInfoRequest request) {
        Long usuarioID = usuarioPersistencePort.obtenerUsuarioID();
        Carrito carrito = carritoPersistencePort.obtenerOCrearCarrito(usuarioID);

        List<ArticuloCarrito> articulosCarrito = articuloCarritoPersistencePort.obtenerArticulosPorCarrito(carrito.getCarritoID());
        List<Long> articuloIds = articulosCarrito.stream()
                .map(ArticuloCarrito::getArticuloId)
                .toList();

        request.setArticuloIds(articuloIds);

        ArticuloCarritoInfoResponseWrapper response = articuloPersistencePort.getArticulosByIds(request);
        String fechaAbastecimiento = suministroPersistencePort.getFechaAbastecimiento();

        double precioTotal = 0;

        for (ArticuloCarritoInfoResponseWrapper.ArticuloCarritoInfoResponse articulo : response.getContent()) {
            Long cantidadEnCarrito = obtenerCantidadEnCarrito(articulo.getArticuloID());
            if (articulo.getCantidad() < cantidadEnCarrito) {
                throw new StockInsuficienteException(fechaAbastecimiento);
            }
            articulo.setCantidad(cantidadEnCarrito);
            precioTotal += articulo.getPrecio() * cantidadEnCarrito;
        }

        BigDecimal precioTotalRedondeado = new BigDecimal(precioTotal).setScale(2, RoundingMode.HALF_UP);

        return new ArticuloCarritoInfoResponseWrapper(precioTotalRedondeado.doubleValue(), response.getContent(), response.getPage(), response.getPageSize(), response.getTotalPages(), response.getTotalCount());
    }

    private ArticuloInfo verificarStockDisponible(Long articuloID, Long cantidad) {
        ArticuloInfo articuloInfo = articuloPersistencePort.verificarInfoArticulo(articuloID);
        String fechaAbastecimiento = suministroPersistencePort.getFechaAbastecimiento();

        if (articuloInfo.getCantidad() <= cantidad) {
            throw new StockInsuficienteException(fechaAbastecimiento);
        }
        return articuloInfo;
    }


    private void actualizarArticuloExistente(ArticuloCarrito articuloExistente, Long cantidad, ArticuloInfo articuloInfo) {
        String fechaAbastecimiento = suministroPersistencePort.getFechaAbastecimiento();
        if (articuloInfo.getCantidad() <= cantidad) {
            throw new StockInsuficienteException(fechaAbastecimiento);
        }
        articuloExistente.setCantidad(cantidad);
        articuloCarritoPersistencePort.agregarArticuloACarrito(articuloExistente);
    }

    private void agregarNuevoArticulo(Carrito carrito, Long articuloID, Long cantidad, ArticuloInfo articuloInfo) {
        Set<Long> categoriasNuevas = new HashSet<>(articuloInfo.getCategorias());
        Map<Long, Set<Long>> articuloCategoriasEnCarrito = obtenerCategoriasDeArticulosEnCarrito(carrito);

        validarMaximoArticulosPorCategoria(categoriasNuevas, articuloCategoriasEnCarrito);

        ArticuloCarrito articuloCarrito = new ArticuloCarrito(carrito.getCarritoID(), articuloID, cantidad);
        articuloCarritoPersistencePort.agregarArticuloACarrito(articuloCarrito);
    }

    private void validarMaximoArticulosPorCategoria(Set<Long> categoriasNuevas, Map<Long, Set<Long>> articuloCategoriasEnCarrito) {
        Map<Long, Long> categoriaContador = obtenerContadorPorCategoria(articuloCategoriasEnCarrito);

        for (Long categoriaNueva : categoriasNuevas) {
            Long count = categoriaContador.getOrDefault(categoriaNueva, CarritoConstants.INICIAL_CATEGORIA_CONTADOR);
            if (count >= CarritoConstants.MAXIMO_ARTICULOS_POR_CATEGORIA) {
                throw new MaximoArticulosPorCategoriaException();
            }
        }
    }

    private Map<Long, Set<Long>> obtenerCategoriasDeArticulosEnCarrito(Carrito carrito) {
        List<ArticuloCarrito> articulosCarrito = articuloCarritoPersistencePort.obtenerArticulosPorCarrito(carrito.getCarritoID());

        Map<Long, Set<Long>> articuloCategoriasEnCarrito = new HashMap<>();
        for (ArticuloCarrito articuloCarrito : articulosCarrito) {
            ArticuloInfo articuloInfo = articuloPersistencePort.verificarInfoArticulo(articuloCarrito.getArticuloId());
            articuloCategoriasEnCarrito.put(articuloCarrito.getArticuloId(), new HashSet<>(articuloInfo.getCategorias()));
        }
        return articuloCategoriasEnCarrito;
    }

    private Map<Long, Long> obtenerContadorPorCategoria(Map<Long, Set<Long>> articuloCategorias) {
        Map<Long, Long> contadorCategorias = new HashMap<>();

        for (Map.Entry<Long, Set<Long>> entry : articuloCategorias.entrySet()) {
            Set<Long> categoriasArticulo = entry.getValue();
            for (Long categoriaID : categoriasArticulo) {
                contadorCategorias.put(categoriaID, contadorCategorias.getOrDefault(categoriaID, CarritoConstants.INICIAL_CATEGORIA_CONTADOR)
                        + CarritoConstants.INCREMENTO_CATEGORIA_CONTADOR);
            }
        }

        return contadorCategorias;
    }

    private Long obtenerCantidadEnCarrito(Long articuloID) {
        Long usuarioID = usuarioPersistencePort.obtenerUsuarioID();
        Carrito carrito = carritoPersistencePort.obtenerOCrearCarrito(usuarioID);
        ArticuloCarrito articuloCarrito = articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carrito.getCarritoID(), articuloID);
        return articuloCarrito != null ? articuloCarrito.getCantidad() : 0L;
    }
}