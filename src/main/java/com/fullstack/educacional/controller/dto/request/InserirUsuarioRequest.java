package com.fullstack.educacional.controller.dto.request;

public record InserirUsuarioRequest(
        String nomeUsuario,
        String senha,
        String nomePapel
) {
}
