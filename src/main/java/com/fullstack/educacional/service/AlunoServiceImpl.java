package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.response.PontuacaoResponse;
import com.fullstack.educacional.datasource.entity.*;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import com.fullstack.educacional.datasource.repository.NotaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl extends GenericServiceImpl<AlunoEntity, AlunoEntity, AlunoRepository> implements GenericService<AlunoEntity, AlunoEntity> {
    private final AlunoRepository repository;
    private final NotaRepository notaRepository;

    public AlunoServiceImpl(AlunoRepository repository, NotaRepository notaRepository) {
        super(repository, new AlunoEntity());
        this.repository = repository;
        this.notaRepository = notaRepository;
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

    public List<NotaEntity> getAllNotas(Long alunoId) {
        AlunoEntity aluno = repository.findById(alunoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Nenhum aluno encontrado com ID: " + alunoId));

        return notaRepository.findAllByAluno(aluno)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Nenhuma nota encontrada com aluno de nomeAluno: " + aluno.getNome()));
    }

    public PontuacaoResponse getPontuacaoTotal(Long alunoId) {
        return null;
        //TODO: criar lógica para pontuação total
    }
}
