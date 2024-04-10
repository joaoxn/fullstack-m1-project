package com.fullstack.educacional.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
public class MateriaEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate dataEntrada;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private CursoEntity curso;
}
