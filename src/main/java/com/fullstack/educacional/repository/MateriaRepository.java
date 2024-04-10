package com.fullstack.educacional.repository;

import com.fullstack.educacional.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
}
