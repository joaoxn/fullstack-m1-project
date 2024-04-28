package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.NotaRequest;
import com.fullstack.educacional.datasource.entity.*;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import com.fullstack.educacional.datasource.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class NotaServiceImpl extends GenericServiceImpl<NotaEntity, NotaRequest, NotaRepository> implements GenericService<NotaEntity, NotaRequest> {
    private final AlunoRepository alunoRepository;
    private final DocenteRepository docenteRepository;
    private final MateriaRepository materiaRepository;

    public NotaServiceImpl(
            NotaRepository repository,
            AlunoRepository alunoRepository,
            DocenteRepository docenteRepository,
            MateriaRepository materiaRepository
    ) {
        super(repository, new NotaEntity());
        this.alunoRepository = alunoRepository;
        this.docenteRepository = docenteRepository;
        this.materiaRepository = materiaRepository;
    }

    @Override
    public NotaEntity equalProperties(NotaEntity entity, NotaRequest data) {
        Float valor = data.valor();
        if (valor != null) {
            entity.setValor(valor);
        }

        AlunoEntity aluno = null;
        try {
            aluno = alunoRepository.findById(data.alunoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (ResponseStatusException ignore) {}
        if (aluno != null) {
            entity.setAluno(aluno);
        }

        DocenteEntity docente = null;
        try {
            docente = docenteRepository.findById(data.docenteId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (ResponseStatusException ignore) {}
        if (docente != null) {
            entity.setDocente(docente);
        }

        MateriaEntity materia = null;
        try {
            materia = materiaRepository.findById(data.materiaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } catch (ResponseStatusException ignore) {}
        if (materia != null) {
            entity.setMateria(materia);
        }

        if (entity.getDataEntrada() == null) {
            entity.setDataEntrada(LocalDate.now());
        }

        return entity;
    }
}
