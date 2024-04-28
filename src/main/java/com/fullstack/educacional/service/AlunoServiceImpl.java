package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.AlunoRequest;
import com.fullstack.educacional.controller.dto.response.PontuacaoResponse;
import com.fullstack.educacional.datasource.entity.*;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import com.fullstack.educacional.datasource.repository.NotaRepository;
import com.fullstack.educacional.datasource.repository.TurmaRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class AlunoServiceImpl extends GenericServiceImpl<AlunoEntity, AlunoRequest, AlunoRepository> implements GenericService<AlunoEntity, AlunoRequest> {
    private final AlunoRepository repository;
    private final TurmaRepository turmaRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotaRepository notaRepository;
    private final TokenService tokenService;

    public AlunoServiceImpl(
            AlunoRepository repository,
            TurmaRepository turmaRepository,
            UsuarioRepository usuarioRepository,
            NotaRepository notaRepository,
            TokenService tokenService
    ) {
        super(repository, new AlunoEntity());
        this.repository = repository;
        this.turmaRepository = turmaRepository;
        this.usuarioRepository = usuarioRepository;
        this.notaRepository = notaRepository;
        this.tokenService = tokenService;
    }

    public AlunoEntity get(Long id, String token) {
        validarPermissaoAluno(id, token);
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Aluno não encontrado com ID: " + id));
    }

    @Override
    public AlunoEntity create(AlunoRequest alunoRequest) {
        if (!usuarioRepository.findByLogin(alunoRequest.login())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getPapel().getNome().equals("ALUNO")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tentou criar aluno com um usuário sem papel de ALUNO");
        }
        return repository.save(equalProperties(new AlunoEntity(), alunoRequest));
    }

    @Override
    public AlunoEntity equalProperties(AlunoEntity entity, AlunoRequest data) {
        String nome = data.nome();
        if (nome != null) {
            entity.setNome(nome);
        }
        TurmaEntity turma = null;
        try {
            turma = turmaRepository.findById(data.turmaId())
                    .orElseThrow();
        } catch (RuntimeException ignore) {}
        if (turma != null) {
            entity.setTurma(turma);
        }

        UsuarioEntity usuario = null;
        try {
            usuario = usuarioRepository.findByLogin(data.login())
                    .orElseThrow();
        } catch (RuntimeException ignore) {}
        if (usuario != null) {
            entity.setUsuario(usuario);
        }

        LocalDate dataNascimento = data.dataNascimento();
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
                        HttpStatus.NOT_FOUND, "Nenhuma nota encontrada com aluno ["+ aluno.getId() +"] de nome: " + aluno.getNome()));
    }

    public PontuacaoResponse getPontuacaoTotal(Long alunoId) {
        return null;
        //TODO: criar lógica para pontuação total
    }

    public void validarPermissaoAluno(Long id, String token) {
        Long usuarioId = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );
        String usuarioScope = String.valueOf(
                tokenService.buscaCampo(token, "scope")
        );

        if (usuarioScope.equals("ALUNO") &&
                !usuarioId.equals(
                        repository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado aluno com ID: "+ id))
                                .getUsuario()
                                .getId()
                )) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Você não tem acesso a essas informações por não serem suas. (Tentou acessar aluno com id: "+ id +")");
        }
    }
}
