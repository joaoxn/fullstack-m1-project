package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.DocenteRequest;
import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.AlunoRepository;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class DocenteServiceImpl extends GenericServiceImpl<DocenteEntity, DocenteRequest, DocenteRepository> implements GenericService<DocenteEntity, DocenteRequest> {

    private final UsuarioRepository usuarioRepository;

    public DocenteServiceImpl(DocenteRepository repository, UsuarioRepository usuarioRepository) {
        super(repository, new DocenteEntity());
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public DocenteEntity equalProperties(DocenteEntity entity, DocenteRequest data) {
        String Nome = data.nome();
        if (Nome != null) {
            entity.setNome(Nome);
        }

        UsuarioEntity usuario = null;
        try {
            usuario = usuarioRepository.findByNome(data.nomeUsuario())
                    .orElseThrow();
        } catch (ResponseStatusException ignored) {}
        if (usuario != null) {
            entity.setUsuario(usuario);
        }
        if (entity.getDataEntrada() == null) {
            entity.setDataEntrada(LocalDate.now());
        }

        return entity;
    }
}
