package com.fullstack.educacional.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Table
@Data
@Entity
public class PapelEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;
}
