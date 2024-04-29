package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.AlunoRequest;
import com.fullstack.educacional.controller.dto.response.PontuacaoResponse;
import com.fullstack.educacional.datasource.entity.*;
import com.fullstack.educacional.datasource.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fullstack.educacional.infra.exception.CustomErrorException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl extends GenericServiceImpl<AlunoEntity, AlunoRequest, AlunoRepository> implements GenericService<AlunoEntity, AlunoRequest> {
    private final AlunoRepository repository;
    private final TurmaRepository turmaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MateriaRepository materiaRepository;
    private final NotaRepository notaRepository;
    private final TokenService tokenService;

    public AlunoServiceImpl(
            AlunoRepository repository,
            TurmaRepository turmaRepository,
            UsuarioRepository usuarioRepository,
            MateriaRepository materiaRepository,
            NotaRepository notaRepository,
            TokenService tokenService
    ) {
        super(repository);
        this.repository = repository;
        this.turmaRepository = turmaRepository;
        this.usuarioRepository = usuarioRepository;
        this.materiaRepository = materiaRepository;
        this.notaRepository = notaRepository;
        this.tokenService = tokenService;
    }

    public AlunoEntity newEntity() {
        return new AlunoEntity();
    }

    public AlunoEntity get(Long id, String token) {
        validarPermissaoAluno(id, token);
        return repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Aluno não encontrado com ID: " + id));
    }

    @Override
    public AlunoEntity create(AlunoRequest alunoRequest) {
        if (!usuarioRepository.findByLogin(alunoRequest.login())
                .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND))
                .getPapel().getNome().equals("ALUNO")) {
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Tentou criar aluno com um usuário sem papel de ALUNO");
        }
        return repository.save(equalProperties(new AlunoEntity(), alunoRequest));
    }

    public AlunoEntity equalProperties(AlunoEntity entity, AlunoRequest data) {
        String nome = data.nome();
        if (nome != null) {
            entity.setNome(nome);
        }
        TurmaEntity turma = null;
        try {
            turma = turmaRepository.findById(data.turmaId())
                    .orElseThrow();
        } catch (RuntimeException ignore) {
        }
        if (turma != null) {
            entity.setTurma(turma);
        }

        UsuarioEntity usuario = null;
        try {
            usuario = usuarioRepository.findByLogin(data.login())
                    .orElseThrow();
        } catch (RuntimeException ignore) {
        }
        if (usuario != null) {
            entity.setUsuario(usuario);
        }

        LocalDate dataNascimento = data.dataNascimento();
        if (dataNascimento != null) {
            entity.setDataNascimento(dataNascimento);
        }

        return entity;
    }

    public List<NotaEntity> getAllNotas(Long id, String token) {
        validarPermissaoAluno(id, token);

        AlunoEntity aluno = repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Nenhum aluno encontrado com ID: " + id));

        return notaRepository.findAllByAluno(aluno);
    }

    public PontuacaoResponse getPontuacaoTotal(Long id, String token) {
        validarPermissaoAluno(id, token);

        AlunoEntity aluno = repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "Aluno não encontrado com ID: " + id));
        List<NotaEntity> notas = notaRepository.findAllByAluno(aluno);
        List<MateriaEntity> materias = materiaRepository.findAllByCurso(aluno.getTurma().getCurso());

        Float somaNotas = 0F;
        for (NotaEntity nota : notas) {
            somaNotas += nota.getValor();
        }
        return new PontuacaoResponse(somaNotas / materias.size() * 10);
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
                                .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "Não encontrado aluno com ID: " + id))
                                .getUsuario()
                                .getId()
                )) {
            throw new CustomErrorException(HttpStatus.UNAUTHORIZED, "Você não tem acesso a essas informações por não serem suas. (Tentou acessar aluno com id: " + id + ")");
        }
    }
}
