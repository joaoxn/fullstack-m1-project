package com.fullstack.educacional.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import com.fullstack.educacional.infra.exception.CustomErrorException;

import java.util.List;

@Data
@RequiredArgsConstructor
public abstract class GenericServiceImpl<E, DTO, R extends JpaRepository<E, Long>> {

    private final R repository;

    public E get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E create(DTO entity) {
        E entitySave = equalProperties(newEntity(), entity);
        return repository.save(entitySave);
    }

    public E alter(Long id, DTO data) {
        E entityFound = repository.findById(id)
                .orElseThrow(
                        () -> new CustomErrorException(
                                HttpStatus.NOT_FOUND,
                                "Entidade não encontrada com ID: " + id));

        return repository.save(equalProperties(entityFound, data));
    }

    public String delete(Long id) {
        E entity = repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
        repository.delete(entity);
        return "Entidade com ID: "+ id +" deletado com sucesso!";
    }

    public abstract E equalProperties(E entity, DTO data);

    public abstract E newEntity();
}
