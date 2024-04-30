package com.fullstack.educacional.controller.dto.request;

public record TurmaRequest(
    String nome,
    Long docenteId,
    Long cursoId
) {
}
