package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.TurmaRequest;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.repository.TurmaRepository;
import org.springframework.stereotype.Service;

@Service
public class TurmaServiceImpl extends GenericServiceImpl<TurmaEntity, TurmaRequest, TurmaRepository> implements GenericService<TurmaEntity, TurmaRequest> {

    public TurmaServiceImpl(TurmaRepository repository) {
        super(repository, new TurmaEntity());
    }

    @Override
    public TurmaEntity equalProperties(TurmaEntity entity, TurmaRequest data) {
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
