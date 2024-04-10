package com.fullstack.educacional.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
public class TurmaEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private LocalDate dataEntrada;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private CursoEntity curso;
}
