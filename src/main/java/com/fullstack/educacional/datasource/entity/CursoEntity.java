package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table
@Data
@Entity
public class CursoEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;

    private LocalDate dataEntrada;
}
