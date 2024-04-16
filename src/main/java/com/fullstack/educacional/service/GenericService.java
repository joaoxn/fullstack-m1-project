package com.fullstack.educacional.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GenericService<E, R extends JpaRepository<E, Long>> {

    final R repository;

    public GenericService(R repository) {
        this.repository = repository;
    }

    E get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
    }

    List<E> getAll() {
        return repository.findAll();
    }

    E create(E entity) {
        return repository.save(entity);
    }

    E alter(Long id, E entity) {
        E entityFound = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));

        return repository.save(equalProperties(entityFound, entity));
    }

    E equalProperties(E entity, E data) {
//        String Nome = data.getNome();
//        if (Nome != null) {
//            entity.setNome(Nome);
//        }
//
//        TurmaEntity turma = data.getTurma();
//        if (turma != null) {
//            entity.setTurma(turma);
//        }
//
//        UsuarioEntity usuario = data.getUsuario();
//        if (usuario != null) {
//            entity.setUsuario(usuario);
//        }
//
//        LocalDate dataNascimento = data.getDataNascimento();
//        if (dataNascimento != null) {
//            entity.setDataNascimento(dataNascimento);
//        }

        return entity;
    }

    void delete(Long id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
        repository.delete(entity);
    }}
