package com.fullstack.educacional.controller.dto.request;

public record UsuarioRequest(
        String nomeUsuario,
        String senha,
        String nomePapel
) {
}
