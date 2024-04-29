package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.TurmaRequest;
import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.repository.CursoRepository;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import com.fullstack.educacional.datasource.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fullstack.educacional.infra.exception.CustomErrorException;

@Service
public class TurmaServiceImpl extends GenericServiceImpl<TurmaEntity, TurmaRequest, TurmaRepository> implements GenericService<TurmaEntity, TurmaRequest> {
    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;

    public TurmaServiceImpl(
            TurmaRepository repository,
            CursoRepository cursoRepository,
            DocenteRepository docenteRepository
    ) {
        super(repository);
        this.cursoRepository = cursoRepository;
        this.docenteRepository = docenteRepository;
    }

    public TurmaEntity create(TurmaRequest entity) {
        TurmaEntity entitySave = equalProperties(newEntity(), entity);
        NullSafeSave<TurmaEntity, TurmaRepository> nullSafeSave = new NullSafeSave<>(getRepository());
        return nullSafeSave.save(entitySave);
    }

    public TurmaEntity newEntity() {
        return new TurmaEntity();
    }

    public TurmaEntity equalProperties(TurmaEntity entity, TurmaRequest data) {
        String nome = data.nome();
        if (nome != null) {
            entity.setNome(nome);
        }

        DocenteEntity docente = null;
        try {
            docente = docenteRepository.findById(data.docenteId())
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND));

            if (!docente.getUsuario().getPapel().getNome().equals("PROFESSOR")) {
                throw new CustomErrorException(HttpStatus.BAD_REQUEST);
            }
        } catch (CustomErrorException e) {
            docente = null;
        }
        if (docente != null) {
            entity.setDocente(docente);
        }

        CursoEntity curso = null;
        try {
            curso = cursoRepository.findById(data.cursoId())
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND));
        } catch (CustomErrorException ignore) {}
        if (curso != null) {
            entity.setCurso(curso);
        }

        return entity;
    }
}
