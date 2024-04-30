package com.fullstack.educacional.datasource.repository;

import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
    List<MateriaEntity> findAllByCurso(CursoEntity curso);
}
