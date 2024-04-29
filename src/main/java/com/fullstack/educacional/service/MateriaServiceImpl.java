package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.MateriaRequest;
import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.repository.CursoRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MateriaServiceImpl extends GenericServiceImpl<MateriaEntity, MateriaRequest, MateriaRepository> implements GenericService<MateriaEntity, MateriaRequest> {
    private final CursoRepository cursoRepository;

    public MateriaServiceImpl(MateriaRepository repository, CursoRepository cursoRepository) {
        super(repository, new MateriaEntity());
        this.cursoRepository = cursoRepository;
    }

    @Override
    public MateriaEntity equalProperties(MateriaEntity entity, MateriaRequest data) {
        String nome = data.nome();
        if (nome != null) {
            entity.setNome(nome);
        }

        CursoEntity curso = null;
        try {
            curso = cursoRepository.findByNome(data.nomeCurso())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (ResponseStatusException ignore) {}
        if (curso != null) {
            entity.setCurso(curso);
        }

        return entity;
    }
}
