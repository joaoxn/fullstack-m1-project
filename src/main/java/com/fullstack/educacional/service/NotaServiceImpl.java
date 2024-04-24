package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.NotaRequest;
import com.fullstack.educacional.datasource.entity.NotaEntity;
import com.fullstack.educacional.datasource.repository.NotaRepository;
import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl extends GenericServiceImpl<NotaEntity, NotaRequest, NotaRepository> implements GenericService<NotaEntity, NotaRequest> {

    public NotaServiceImpl(NotaRepository repository) {
        super(repository, new NotaEntity());
    }

    @Override
    public NotaEntity equalProperties(NotaEntity entity, NotaRequest data) {
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
