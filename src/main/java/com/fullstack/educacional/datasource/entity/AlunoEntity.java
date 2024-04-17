package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
@Entity
public class AlunoEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private TurmaEntity turma;
}
