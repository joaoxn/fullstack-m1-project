package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.CursoRequest;
import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.repository.CursoRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class CursoServiceImpl extends GenericServiceImpl<CursoEntity, CursoRequest, CursoRepository> implements GenericService<CursoEntity, CursoRequest> {
    private final MateriaRepository materiaRepository;
    private final CursoRepository repository;

    public CursoServiceImpl(MateriaRepository materiaRepository, CursoRepository repository) {
        super(repository, new CursoEntity());
        this.materiaRepository = materiaRepository;
        this.repository = repository;
    }

    @Override
    public CursoEntity equalProperties(CursoEntity entity, CursoRequest data) {
        String Nome = data.nome();
        if (Nome != null) {
            entity.setNome(Nome);
        }

        return entity;
    }

    public List<MateriaEntity> getAllMaterias(Long cursoId) {
        CursoEntity curso = repository.findById(cursoId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Nenhum curso encontrado com ID: " + cursoId));

        return materiaRepository.findAllByCurso(curso)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Nenhuma matéria encontrada com curso de nomeAluno: " + curso.getNome()));
    }
}
