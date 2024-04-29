package com.fullstack.educacional.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstack.educacional.controller.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

@Table(name = "usuario")
@Data
@Entity
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "papel_id", nullable = false)
    private PapelEntity papel;

    public boolean senhaValida(LoginRequest loginRequest, BCryptPasswordEncoder bCryptEncoder) {
        return bCryptEncoder.matches(
                loginRequest.senha(),
                this.senha
        );
    }
}
