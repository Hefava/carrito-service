package com.bootcamp.carrito_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarritoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoServiceApplication.class, args);
	}

}
