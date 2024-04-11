package com.fullstack.educacional.datasource.repository;

import com.fullstack.educacional.datasource.entity.PapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapelRepository extends JpaRepository<PapelEntity, Long> {
}
