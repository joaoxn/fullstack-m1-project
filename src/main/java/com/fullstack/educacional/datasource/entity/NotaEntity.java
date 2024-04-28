package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "nota")
@Data
@Entity
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private MateriaEntity materia;

    @Column(nullable = false)
    private Float valor;

    private LocalDate dataEntrada;
}
