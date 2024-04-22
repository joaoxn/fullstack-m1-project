package com.fullstack.educacional.datasource.repository;

import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<List<UsuarioEntity>> findByPapelId(Long papelId);

    Optional<UsuarioEntity> findByLogin(String login);
}
