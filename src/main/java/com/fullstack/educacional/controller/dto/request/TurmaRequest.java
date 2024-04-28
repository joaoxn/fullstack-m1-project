package com.fullstack.educacional.controller.dto.request;

public record TurmaRequest(
    String nome,
    String loginDocente,
    String nomeCurso
) {
}
