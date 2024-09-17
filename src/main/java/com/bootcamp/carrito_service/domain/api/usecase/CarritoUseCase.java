package com.bootcamp.carrito_service.domain.api.usecase;

import com.bootcamp.carrito_service.domain.api.ICarritoServicePort;
import com.bootcamp.carrito_service.domain.exception.MaximoArticulosPorCategoriaException;
import com.bootcamp.carrito_service.domain.exception.StockInsuficienteException;
import com.bootcamp.carrito_service.domain.model.ArticuloCarrito;
import com.bootcamp.carrito_service.domain.model.Carrito;
import com.bootcamp.carrito_service.domain.spi.IArticuloCarritoPersistencePort;
import com.bootcamp.carrito_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.carrito_service.domain.spi.ICarritoPersistencePort;
import com.bootcamp.carrito_service.domain.spi.IUsuarioPersistencePort;
import com.bootcamp.carrito_service.domain.utils.ArticuloInfo;

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

    public CarritoUseCase(IArticuloPersistencePort articuloPersistencePort, ICarritoPersistencePort carritoPersistencePort, IUsuarioPersistencePort usuarioPersistencePort, IArticuloCarritoPersistencePort articuloCarritoPersistencePort) {
        this.articuloPersistencePort = articuloPersistencePort;
        this.carritoPersistencePort = carritoPersistencePort;
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.articuloCarritoPersistencePort = articuloCarritoPersistencePort;
    }

    @Override
    public void agregarArticulo(Long articuloID, Long cantidad) {
        Long usuarioID = usuarioPersistencePort.obtenerUsuarioID();
        Carrito carrito = carritoPersistencePort.obtenerOCrearCarrito(usuarioID);

        ArticuloInfo articuloInfo = articuloPersistencePort.verificarInfoArticulo(articuloID);
        if (articuloInfo.getCantidad() <= cantidad) {
            throw new StockInsuficienteException("No hay suficiente stock para este artículo.");
        }

        ArticuloCarrito articuloExistente = articuloCarritoPersistencePort.obtenerArticuloEnCarrito(carrito.getCarritoID(), articuloID);

        if (articuloExistente != null) {
            if (articuloInfo.getCantidad() <= cantidad) {
                throw new StockInsuficienteException("No hay suficiente stock para la cantidad solicitada.");
            }
            articuloExistente.setCantidad(cantidad);
            articuloCarritoPersistencePort.agregarArticuloACarrito(articuloExistente);
        }
        else {
            Set<Long> categoriasNuevas = new HashSet<>(articuloInfo.getCategorias());

            Map<Long, Set<Long>> articuloCategoriasEnCarrito = obtenerCategoriasDeArticulosEnCarrito(carrito);

            validarMaximoArticulosPorCategoria(categoriasNuevas, articuloCategoriasEnCarrito);

            ArticuloCarrito articuloCarrito = new ArticuloCarrito(carrito.getCarritoID(), articuloID, cantidad);
            articuloCarritoPersistencePort.agregarArticuloACarrito(articuloCarrito);
        }

        carritoPersistencePort.actualizarCarrito(carrito);
    }


    private void validarMaximoArticulosPorCategoria(Set<Long> categoriasNuevas, Map<Long, Set<Long>> articuloCategoriasEnCarrito) {
        Map<Long, Long> categoriaContador = obtenerContadorPorCategoria(articuloCategoriasEnCarrito);

        for (Long categoriaNueva : categoriasNuevas) {
            Long count = categoriaContador.getOrDefault(categoriaNueva, 0L);
            if (count >= 3) {
                throw new MaximoArticulosPorCategoriaException("Ya tienes 3 artículos de la categoría: " + categoriaNueva);
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
                contadorCategorias.put(categoriaID, contadorCategorias.getOrDefault(categoriaID, 0L) + 1);
            }
        }

        return contadorCategorias;
    }
}