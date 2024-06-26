package com.fullstack.educacional.datasource.repository;

import com.fullstack.educacional.datasource.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
    Optional<DocenteEntity> findByUsuarioId(Long id);
}
