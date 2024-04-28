package com.fullstack.educacional.controller.dto.response;

import com.fullstack.educacional.datasource.entity.PapelEntity;

public record UsuarioResponse(
        Long id,
        String login,
        PapelEntity papel
) {
}
