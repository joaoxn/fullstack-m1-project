package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AlunoRepository repository;

    private AlunoEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
    }

    private List<AlunoEntity> getAll() {
        return repository.findAll();
    }

    private AlunoEntity create(AlunoEntity entity) {
        return repository.save(entity);
    }

    private AlunoEntity alter(Long id, AlunoEntity entity) {
        AlunoEntity entityFound = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));

        return repository.save(equalProperties(entityFound, entity));
    }

    private AlunoEntity equalProperties(AlunoEntity entity, AlunoEntity data) {
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

    private void delete(Long id) {
        AlunoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
        repository.delete(entity);
    }
}
