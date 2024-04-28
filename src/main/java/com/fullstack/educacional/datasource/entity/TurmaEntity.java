package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "turma")
@Data
@Entity
public class TurmaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataEntrada;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoEntity curso;
}
