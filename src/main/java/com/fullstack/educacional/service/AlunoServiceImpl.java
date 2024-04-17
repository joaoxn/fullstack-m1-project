package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AlunoServiceImpl extends GenericServiceImpl<AlunoEntity, AlunoRepository> implements GenericService<AlunoEntity> {


    public AlunoServiceImpl(AlunoRepository repository) {
        super(repository);
    }

    @Override
    public AlunoEntity equalProperties(AlunoEntity entity, AlunoEntity data) {
        String Nome = data.getNome();
        if (Nome != null) {
            entity.setNome(Nome);
        }

        TurmaEntity turma = data.getTurma();
        if (turma != null) {
            entity.setTurma(turma);
        }

        UsuarioEntity usuario = data.getUsuario();
        if (usuario != null) {
            entity.setUsuario(usuario);
        }

        LocalDate dataNascimento = data.getDataNascimento();
        if (dataNascimento != null) {
            entity.setDataNascimento(dataNascimento);
        }

        return entity;
    }
}
