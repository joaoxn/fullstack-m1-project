package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.repository.CursoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Data
@RequiredArgsConstructor
public abstract class GenericServiceImpl<E, DTO, R extends JpaRepository<E, Long>> {

    private final R repository;
    private final E newEntity;

    public E get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E create(DTO entity) {
        return repository.save(equalProperties(newEntity, entity));
    }

    public E alter(Long id, DTO data) {
        E entityFound = repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Entidade não encontrada com ID: " + id));

        return repository.save(equalProperties(entityFound, data));
    }

    public abstract E equalProperties(E entity, DTO data);

    public void delete(Long id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
        repository.delete(entity);
    }
}
