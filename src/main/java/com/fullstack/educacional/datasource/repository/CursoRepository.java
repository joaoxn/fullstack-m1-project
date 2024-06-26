package com.fullstack.educacional.datasource.repository;

import com.fullstack.educacional.datasource.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
}
