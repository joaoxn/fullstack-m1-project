package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository repository;

    private DocenteEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
    }

    private List<DocenteEntity> getAll() {
        return repository.findAll();
    }

    private DocenteEntity create(DocenteEntity entity) {
        return repository.save(entity);
    }

    private DocenteEntity alter(Long id, DocenteEntity entity) {
        DocenteEntity entityFound = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));

        return repository.save(equalProperties(entityFound, entity));
    }

    private DocenteEntity equalProperties(DocenteEntity entity, DocenteEntity data) {
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

    private void delete(Long id) {
        DocenteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
        repository.delete(entity);
    }
}
