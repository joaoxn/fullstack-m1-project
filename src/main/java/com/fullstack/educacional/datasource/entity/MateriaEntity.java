package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
@Entity
public class MateriaEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate dataEntrada;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
}
