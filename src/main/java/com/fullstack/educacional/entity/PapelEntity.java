package com.fullstack.educacional.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Table
@Data
public class PapelEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private Long id;

    private String nome;
}
