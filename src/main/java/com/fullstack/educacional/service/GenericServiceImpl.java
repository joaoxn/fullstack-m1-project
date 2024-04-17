package com.fullstack.educacional.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GenericServiceImpl<E, R extends JpaRepository<E, Long>> {

    private final R repository;

    public E get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E create(E entity) {
        return repository.save(entity);
    }

    public E alter(Long id, E entity) {
        E entityFound = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));

        return repository.save(equalProperties(entityFound, entity));
    }

    public E equalProperties(E entity, E data) {
        return entity;
    }

    public void delete(Long id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada com ID: " + id));
        repository.delete(entity);
    }}
