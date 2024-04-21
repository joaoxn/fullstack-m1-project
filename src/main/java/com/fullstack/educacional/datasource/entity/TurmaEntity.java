package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
@Entity
public class TurmaEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private LocalDate dataEntrada;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
}
