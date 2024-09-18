package com.bootcamp.carrito_service.ports.application.http;

import com.bootcamp.carrito_service.domain.api.ICarritoServicePort;
import com.bootcamp.carrito_service.ports.application.http.controller.CarritoRestController;
import com.bootcamp.carrito_service.ports.application.http.dto.CarritoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarritoRestController.class)
class CarritoRestControllerTest {

    @MockBean
    private ICarritoServicePort carritoService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAgregarArticulos_Unauthorized() throws Exception {
        // Arrange
        CarritoRequest carritoRequest = new CarritoRequest();
        carritoRequest.setArticuloID(1L);
        carritoRequest.setCantidad(5L);

        // Act & Assert
        mockMvc.perform(post("/carrito/agregar-articulos")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer invalid-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carritoRequest)))
                .andExpect(status().isForbidden());
    }
}