package com.fullstack.educacional.infra.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class CustomErrorResponse {
    private final int status;
    private final HttpStatusCode erro;
    private final String mensagem;

    public CustomErrorResponse(@NonNull HttpStatusCode error) {
        this.status = error.value();
        this.erro = error;
        this.mensagem = "Um erro inesperado ocorreu";
    }

    public CustomErrorResponse(@NonNull HttpStatusCode error, @NonNull String message) {
        this.status = error.value();
        this.erro = error;
        this.mensagem = message;
    }
}
