package com.fullstack.educacional.controller.dto.request;

public record UsuarioRequest(
        String nome,
        String senha,
        String nomePapel
) {}
