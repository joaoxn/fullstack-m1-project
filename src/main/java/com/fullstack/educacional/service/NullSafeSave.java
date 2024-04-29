package com.fullstack.educacional.service;

import com.fullstack.educacional.infra.exception.CustomErrorException;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.internal.Nullability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;

@RequiredArgsConstructor
public class NullSafeSave<E, R extends JpaRepository<E, Long>> {
    private final R repository;

    public E save(E entity) {
        try {
            return repository.save(entity);
        } catch(Exception e) {
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Faltam informações para salvar o objeto");
        }
    }
}
