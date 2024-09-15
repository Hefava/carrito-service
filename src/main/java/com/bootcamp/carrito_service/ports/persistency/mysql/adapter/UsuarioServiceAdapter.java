package com.bootcamp.carrito_service.ports.persistency.mysql.adapter;

import com.bootcamp.carrito_service.domain.spi.IUsuarioPersistencePort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsuarioServiceAdapter implements IUsuarioPersistencePort {

    @Override
    public Long obtenerUsuarioID() {
        UserDetails usuarioID = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf(usuarioID.getUsername());
    }
}
