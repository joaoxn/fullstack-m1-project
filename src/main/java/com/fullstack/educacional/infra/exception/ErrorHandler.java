package com.fullstack.educacional.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception e) {
        return ResponseEntity.internalServerError().body(new CustomErrorResponse("Erro inesperado: "+e.getMessage(), e.getCause()));
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<?> handler(CustomErrorException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new CustomErrorResponse(e.getStatusCode(), e.getMessage(), e.getCause()));
    }

}