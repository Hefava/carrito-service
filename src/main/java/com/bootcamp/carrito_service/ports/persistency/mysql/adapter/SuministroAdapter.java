package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.spi.ISuministroPersistencePort;
import com.bootcamp.carrito_service.ports.persistency.mysql.repository.ISuministroFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuministroAdapter implements ISuministroPersistencePort {

    private final ISuministroFeign suministroFeign;

    @Override
    public String getFechaAbastecimiento() {
        return suministroFeign.getFechaAbastecimiento();
    }
}