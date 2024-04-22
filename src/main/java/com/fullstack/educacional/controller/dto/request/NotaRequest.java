package com.fullstack.educacional.controller.dto.request;

public record NotaRequest(
        String nome,
        String nomeDocente,
        String nomeMateria,
        Float valor
) {
}
