package com.fullstack.educacional.datasource.repository;

import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    Optional<List<NotaEntity>> findAllByAluno(AlunoEntity aluno);
}
