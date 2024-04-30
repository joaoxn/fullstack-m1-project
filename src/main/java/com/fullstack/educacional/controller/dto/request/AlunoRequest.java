package com.fullstack.educacional.controller.dto.request;

import java.time.LocalDate;

public record AlunoRequest(
        String nome,
        String login,
        Long turmaId,
        LocalDate dataNascimento
) {
}
