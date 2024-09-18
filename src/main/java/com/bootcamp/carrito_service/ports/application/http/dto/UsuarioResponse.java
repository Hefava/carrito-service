package com.bootcamp.carrito_service.ports.application.http.dto;

import lombok.Data;

@Data
public class UsuarioResponse {
    private String username;
    private String role;
    private Boolean authorized;
}