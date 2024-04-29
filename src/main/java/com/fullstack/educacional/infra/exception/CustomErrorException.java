package com.fullstack.educacional.infra.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class CustomErrorException extends RuntimeException {
    private HttpStatusCode statusCode;
    private String message;
    private Throwable cause;

    public CustomErrorException() {}

    public CustomErrorException(String message) {
        this.message = message;
    }

    public CustomErrorException(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public CustomErrorException(HttpStatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public CustomErrorException(HttpStatusCode statusCode, String message, Throwable cause) {
        this.statusCode = statusCode;
        this.message = message;
        this.cause = cause;
    }
}
