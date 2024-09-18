package com.bootcamp.carrito_service.infrastructure.exceptionhandler;

import com.bootcamp.carrito_service.domain.exception.MaximoArticulosPorCategoriaException;
import com.bootcamp.carrito_service.domain.exception.StockInsuficienteException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.bootcamp.carrito_service.domain.utils.ErrorConstants.SERVER_ERROR;

@ControllerAdvice
public class CarritoControllerAdvisor {
    private static final String MESSAGE = "Message";

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<Map<String, String>> handleStockInsuficienteException(StockInsuficienteException ex) {
        String mensaje = CarritoExceptionResponse.STOCK_INSUFICIENTE.getMessage() + ex.getFechaAbastecimiento();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, mensaje));
    }

    @ExceptionHandler(MaximoArticulosPorCategoriaException.class)
    public ResponseEntity<Map<String, String>> handleMaximoArticulosPorCategoriaException(MaximoArticulosPorCategoriaException ex) {
        String message = CarritoExceptionResponse.MAXIMO_ARTICULOS_POR_CATEGORIA.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, message));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleFeignException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Map<String, String>> handleFeignRetryableException(RetryableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Collections.singletonMap(MESSAGE, SERVER_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}