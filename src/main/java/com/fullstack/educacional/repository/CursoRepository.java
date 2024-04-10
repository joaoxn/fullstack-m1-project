package com.fullstack.educacional.repository;

import com.fullstack.educacional.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
}
