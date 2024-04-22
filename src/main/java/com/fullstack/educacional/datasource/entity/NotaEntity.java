package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "nota")
@Data
@Entity
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;

    private Float valor;

    private LocalDate data = LocalDate.now();
}
