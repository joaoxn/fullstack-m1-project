package com.fullstack.educacional.controller.dto.request;

public record UsuarioRequest(
        String login,
        String senha,
        String nomePapel
) {
}
