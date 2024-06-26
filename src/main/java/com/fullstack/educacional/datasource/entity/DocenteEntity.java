package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "docente")
@Data
@Entity
public class DocenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private LocalDate dataEntrada;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;
}
