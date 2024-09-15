package com.bootcamp.carrito_service.infrastructure.security;

import com.bootcamp.carrito_service.ports.application.http.dto.UsuarioResponse;
import com.bootcamp.carrito_service.ports.feign.UsuarioFeign;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioFeign usuarioClient;

    public CustomUserDetailsService(UsuarioFeign usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        UsuarioResponse usuarioResponse = usuarioClient.validateToken(token);

        if (usuarioResponse == null || usuarioResponse.getUsername() == null) {
            throw new UsernameNotFoundException("Token inválido o usuario no encontrado");
        }

        return new org.springframework.security.core.userdetails.User(
                usuarioResponse.getUsername(),
                "",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuarioResponse.getRole()))
        );
    }
}