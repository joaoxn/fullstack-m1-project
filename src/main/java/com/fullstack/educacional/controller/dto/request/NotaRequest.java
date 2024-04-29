package com.fullstack.educacional.controller.dto.request;

public record NotaRequest(
        Long alunoId,
        Long materiaId,
        Float valor
) {
}
