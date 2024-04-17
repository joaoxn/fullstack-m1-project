package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DocenteServiceImpl extends GenericServiceImpl<DocenteEntity, DocenteRepository, DocenteServiceImpl> implements GenericService<DocenteEntity> {


    public DocenteServiceImpl(DocenteServiceImpl service, DocenteRepository repository) {
        super(service, repository);
    }

    @Override
    public DocenteEntity equalProperties(DocenteEntity entity, DocenteEntity data) {
        String Nome = data.getNome();
        if (Nome != null) {
            entity.setNome(Nome);
        }

        UsuarioEntity usuario = data.getUsuario();
        if (usuario != null) {
            entity.setUsuario(usuario);
        }

        LocalDate dataEntrada = data.getDataEntrada();
        if (dataEntrada != null) {
            entity.setDataEntrada(dataEntrada);
        }

        return entity;
    }
}
