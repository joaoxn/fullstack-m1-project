package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
@Entity
public class NotaEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;

    private Integer valor;

    private LocalDate data = LocalDate.now();
}
