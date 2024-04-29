package com.fullstack.educacional.infra.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception e) {
        log.error("Erro Exception inesperado identificado");
        log.debug("Erro inesperado identificado [STATUS {}]: {}", HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return ResponseEntity.internalServerError().body(new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado: "+e.getMessage()));
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handler(ResponseStatusException e) {
        log.error("Erro ResponseStatusException {} identificado", e.getStatusCode().value());
        log.debug("Erro identificado [STATUS {}]: {}", e.getStatusCode().value(), e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(new CustomErrorResponse(e.getStatusCode(), e.getMessage()));
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<?> handler(CustomErrorException e) {
        log.error("Erro {} intencional identificado", e.getStatusCode().value());
        log.debug("Erro intencional identificado [STATUS {}]: {}", e.getStatusCode().value(), e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(new CustomErrorResponse(e.getStatusCode(), e.getMessage()));
    }

}