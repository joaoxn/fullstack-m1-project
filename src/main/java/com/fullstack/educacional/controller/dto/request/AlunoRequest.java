package com.fullstack.educacional.controller.dto.request;

import java.time.LocalDate;

public record AlunoRequest(
        String nome,
        String login,
        String nomeTurma,
        LocalDate dataNascimento
) {
}
