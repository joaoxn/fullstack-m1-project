package com.fullstack.educacional.controller.dto.request;

public record NotaRequest(
        String nomeAluno,
        String nomeDocente,
        String nomeMateria,
        Float valor
) {
}
