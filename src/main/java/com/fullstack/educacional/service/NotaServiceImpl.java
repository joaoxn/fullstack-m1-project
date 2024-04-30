package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.NotaRequest;
import com.fullstack.educacional.datasource.entity.*;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import com.fullstack.educacional.datasource.repository.NotaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fullstack.educacional.infra.exception.CustomErrorException;

import java.time.LocalDate;

@Service
@Slf4j
public class NotaServiceImpl extends GenericServiceImpl<NotaEntity, NotaRequest, NotaRepository> implements GenericService<NotaEntity, NotaRequest> {
    private final AlunoRepository alunoRepository;
    private final DocenteRepository docenteRepository;
    private final MateriaRepository materiaRepository;
    private final TokenService tokenService;

    public NotaServiceImpl(
            NotaRepository repository,
            AlunoRepository alunoRepository,
            DocenteRepository docenteRepository,
            MateriaRepository materiaRepository,
            TokenService tokenService
    ) {
        super(repository);
        this.alunoRepository = alunoRepository;
        this.docenteRepository = docenteRepository;
        this.materiaRepository = materiaRepository;
        this.tokenService = tokenService;
    }

    public NotaEntity create(NotaRequest entity, String token) {
        NotaEntity entitySave = equalProperties(newEntity(), entity);
        if (entitySave.getDocente() == null) {
            Long usuarioId = Long.valueOf(
                    tokenService.buscaCampo(token, "sub")
            );
            entitySave.setDocente(docenteRepository.findByUsuarioId(usuarioId)
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.BAD_REQUEST, "Professor não encontrado")));
        }
        return getRepository().save(entitySave);
    }

    public NotaEntity newEntity() {
        return new NotaEntity();
    }

    @Override
    public NotaEntity equalProperties(NotaEntity entity, NotaRequest data) {
        Float valor = data.valor();
        if (valor != null) {
            entity.setValor(valor);
        } else if (entity.getValor() == null){
            log.warn("{}.equalProperties() -> retornando nota com 'valor' nulo", getClass());
        }

        AlunoEntity aluno = null;
        try {
            aluno = alunoRepository.findById(data.alunoId())
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND));
        } catch (RuntimeException ignore) {}
        if (aluno != null) {
            entity.setAluno(aluno);
        } else if (entity.getAluno() == null){
            log.warn("{}.equalProperties() -> retornando nota com 'aluno' nulo", getClass());
        }

        MateriaEntity materia = null;
        try {
            materia = materiaRepository.findById(data.materiaId())
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND));
        } catch (RuntimeException ignore) {}
        if (materia != null) {
            entity.setMateria(materia);
        } else if (entity.getMateria() == null){
            log.warn("{}.equalProperties() -> retornando nota com 'materia' nulo", getClass());
        }

        if (entity.getDataEntrada() == null) {
            entity.setDataEntrada(LocalDate.now());
        }

        return entity;
    }
}
