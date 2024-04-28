package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.MateriaRequest;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MateriaServiceImpl extends GenericServiceImpl<MateriaEntity, MateriaRequest, MateriaRepository> implements GenericService<MateriaEntity, MateriaRequest> {

    public MateriaServiceImpl(MateriaRepository repository) {
        super(repository, new MateriaEntity());
    }

    @Override
    public MateriaEntity equalProperties(MateriaEntity entity, MateriaRequest data) {
//        String Nome = data.nome();
//        if (Nome != null) {
//            entity.setNome(Nome);
//        }
//
//        if (entity.getDataEntrada() == null) {
//            entity.setDataEntrada(LocalDate.now());
//        } TODO

        return entity;
    }
}
