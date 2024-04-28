package com.fullstack.educacional.controller.dto.request;

public record NotaRequest(
        Long alunoId,
        Long docenteId,
        Long materiaId,
        Float valor
) {
}
