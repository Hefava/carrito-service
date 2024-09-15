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
        if (articuloInfo.getCantidad() < cantidad) {
            throw new StockInsuficienteException("No hay suficiente stock para este artículo.");
        }
        Set<Long> categoriasNuevas = new HashSet<>(articuloInfo.getCategorias());
        Map<Long, Set<Long>> articuloCategoriasEnCarrito = obtenerCategoriasDeArticulosEnCarrito(carrito);
        validarMaximoArticulosPorCategoria(carrito, categoriasNuevas, articuloCategoriasEnCarrito);
        ArticuloCarrito articuloCarrito = new ArticuloCarrito(carrito.getCarritoID(), articuloID, cantidad);
        articuloCarritoPersistencePort.agregarArticuloACarrito(articuloCarrito);
        carrito.agregarArticulo(articuloID, cantidad);
        carritoPersistencePort.actualizarCarrito(carrito);
    }

    private void validarMaximoArticulosPorCategoria(Carrito carrito, Set<Long> categoriasNuevas, Map<Long, Set<Long>> articuloCategoriasEnCarrito) {
        Map<Long, Long> categoriaContador = carrito.obtenerContadorPorCategoria(articuloCategoriasEnCarrito);

        for (Long categoriaNueva : categoriasNuevas) {
            Long count = categoriaContador.getOrDefault(categoriaNueva, 0L);
            if (count >= 3) {
                throw new MaximoArticulosPorCategoriaException("Ya tienes 3 artículos de la categoría: " + categoriaNueva);
            }
        }
    }

    private Map<Long, Set<Long>> obtenerCategoriasDeArticulosEnCarrito(Carrito carrito) {
        Map<Long, Set<Long>> articuloCategoriasEnCarrito = new HashMap<>();
        for (Long articuloID : carrito.getArticulos().keySet()) {
            ArticuloInfo articuloInfo = articuloPersistencePort.verificarInfoArticulo(articuloID);
            articuloCategoriasEnCarrito.put(articuloID, new HashSet<>(articuloInfo.getCategorias()));
        }
        return articuloCategoriasEnCarrito;
    }
}