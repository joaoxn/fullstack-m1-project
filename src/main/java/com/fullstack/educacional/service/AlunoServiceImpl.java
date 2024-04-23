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
    private final TokenService tokenService;

    public AlunoServiceImpl(
            AlunoRepository repository,
            NotaRepository notaRepository,
            TokenService tokenService
    ) {
        super(repository, new AlunoEntity());
        this.repository = repository;
        this.notaRepository = notaRepository;
        this.tokenService = tokenService;
    }

    @Override
    public AlunoEntity get(Long id) {
        validarPermissaoAluno(id, , );
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
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

    public void validarPermissaoAluno(Long id, String token, AlunoEntity alunoEntity) {
        Long usuarioId = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );
        String usuarioScope = String.valueOf(
                tokenService.buscaCampo(token, "scope")
        );

        if (usuarioScope.equals("ALUNO") && !usuarioId.equals(alunoEntity.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Você não tem acesso a essas informações por não serem suas. (Tentou acessar aluno com id: "+ id +")");
        }
    }
}
