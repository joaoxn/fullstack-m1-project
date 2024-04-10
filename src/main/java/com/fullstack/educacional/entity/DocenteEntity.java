package com.fullstack.educacional.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
public class DocenteEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private LocalDate dataEntrada;

    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;
}
