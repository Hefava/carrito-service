package com.bootcamp.carrito_service.infrastructure.configuration;

import com.bootcamp.carrito_service.domain.api.ICarritoServicePort;
import com.bootcamp.carrito_service.domain.api.usecase.CarritoUseCase;
import com.bootcamp.carrito_service.domain.spi.IArticuloCarritoPersistencePort;
import com.bootcamp.carrito_service.domain.spi.IArticuloPersistencePort;
import com.bootcamp.carrito_service.domain.spi.ICarritoPersistencePort;
import com.bootcamp.carrito_service.domain.spi.IUsuarioPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IArticuloPersistencePort articuloPersistencePort;
    private final ICarritoPersistencePort carritoPersistencePort;
    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IArticuloCarritoPersistencePort articuloCarritoPersistencePort;

    @Bean
    public ICarritoServicePort suministroServicePort() {
        return new CarritoUseCase(articuloPersistencePort, carritoPersistencePort, usuarioPersistencePort, articuloCarritoPersistencePort);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}