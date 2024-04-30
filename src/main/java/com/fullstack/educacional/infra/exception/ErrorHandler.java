package com.fullstack.educacional.infra.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = e.getMessage();
        logError(status, message, "Exception ");
        return ResponseEntity.internalServerError().body(new CustomErrorResponse(status, "Erro inesperado: "+message));
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handler(ResponseStatusException e) {
        HttpStatusCode status = e.getStatusCode();
        String message = e.getMessage();
        logError(status, message, "ResponseStatusException ");
        return ResponseEntity.status(status).body(new CustomErrorResponse(status, message));
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<?> handler(CustomErrorException e) {
        HttpStatusCode status = e.getStatusCode();
        String message = e.getMessage();
        logError(status, message, "intencional ");
        return ResponseEntity.status(status).body(new CustomErrorResponse(status, message));
    }

    public void logError(HttpStatusCode status, String message, String type) {
        log.error("Erro {} {} identificado", status.value(), type);
        log.error("STATUS {}: {}", status.value(), message);
        log.debug("Erro {}identificado [STATUS {} {}]: {}", type, status.value(), status, message);
    }
}