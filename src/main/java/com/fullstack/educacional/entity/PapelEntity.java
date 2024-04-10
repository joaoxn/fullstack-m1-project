package com.fullstack.educacional.entity;

import com.fullstack.educacional.enums.Papel;
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

    private Papel nome;
}
