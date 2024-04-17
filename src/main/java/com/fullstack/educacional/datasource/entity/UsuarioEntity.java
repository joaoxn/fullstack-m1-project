package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Table
@Data
@Entity
public class UsuarioEntity implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "papel_id", nullable = false)
    private PapelEntity papel;
}
