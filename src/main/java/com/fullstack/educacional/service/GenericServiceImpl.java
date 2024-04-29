package com.fullstack.educacional.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import com.fullstack.educacional.infra.exception.CustomErrorException;

import java.util.List;

@Data
@RequiredArgsConstructor
@Slf4j
public abstract class GenericServiceImpl<E, DTO, R extends JpaRepository<E, Long>> {

    private final R repository;

    public E get(Long id) {
        log.info("GenericServiceImpl.get({}) -> Buscando entidade por id no repositório", id);
        return repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
    }

    public List<E> getAll() {
        log.info("GenericServiceImpl.getAll() -> Buscando todas as entidades no repositório");
        return repository.findAll();
    }

    public E create(DTO entity) {
        log.info("GenericServiceImpl.create(DTO entity) -> Criando entidade baseado na Requisição dada");
        log.info("SERVICE equalProperties() -> ( Chamada: create(...) ) Transferindo dados da Requisição para uma Entidade própria");
        E entitySave = equalProperties(newEntity(), entity);
        return repository.save(entitySave);
    }

    public E alter(Long id, DTO data) {
        log.info("GenericServiceImpl.alter({}, DTO data) -> Alterando por id baseado na Requisição dada", id);
        E entityFound = repository.findById(id)
                .orElseThrow(
                        () -> new CustomErrorException(
                                HttpStatus.NOT_FOUND,
                                "Entidade não encontrada com ID: " + id));
        log.info("SERVICE equalProperties() -> ( Chamada: alter(...) ) Transferindo dados da Requisição para uma Entidade própria");
        return repository.save(equalProperties(entityFound, data));
    }

    public String delete(Long id) {
        log.info("GenericServiceImpl.delete({}) -> Deletando entidade com id informado", id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
        repository.delete(entity);
        return "Entidade com ID: "+ id +" deletado com sucesso!";
    }

    public abstract E equalProperties(E entity, DTO data);

    public abstract E newEntity();
}
