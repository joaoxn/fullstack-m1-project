package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.MateriaRequest;
import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.repository.CursoRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.fullstack.educacional.infra.exception.CustomErrorException;

@Service
@Slf4j
public class MateriaServiceImpl extends GenericServiceImpl<MateriaEntity, MateriaRequest, MateriaRepository> implements GenericService<MateriaEntity, MateriaRequest> {
    private final CursoRepository cursoRepository;

    public MateriaServiceImpl(MateriaRepository repository, CursoRepository cursoRepository) {
        super(repository);
        this.cursoRepository = cursoRepository;
    }

    public MateriaEntity newEntity() {
        return new MateriaEntity();
    }

    public MateriaEntity equalProperties(MateriaEntity entity, MateriaRequest data) {
        String nome = data.nome();
        if (nome != null) {
            entity.setNome(nome);
        } else if (entity.getNome() == null){
            log.warn("{}.equalProperties() -> retornando matéria com 'nome' nulo", getClass());
        }

        CursoEntity curso = null;
        try {
            curso = cursoRepository.findById(data.cursoId())
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND));
        } catch (RuntimeException ignore) {}
        if (curso != null) {
            entity.setCurso(curso);
        } else if (entity.getCurso() == null){
            log.warn("{}.equalProperties() -> retornando matéria com 'curso' nulo", getClass());
        }

        return entity;
    }
}
