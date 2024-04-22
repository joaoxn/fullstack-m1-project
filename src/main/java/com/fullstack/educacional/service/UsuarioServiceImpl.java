package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.UsuarioRequest;
import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.PapelRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<UsuarioEntity, UsuarioRequest, UsuarioRepository> implements GenericService<UsuarioEntity, UsuarioRequest> {

    private final PapelRepository papelRepository;

    public UsuarioServiceImpl(UsuarioRepository repository, PapelRepository papelRepository) {
        super(repository, new UsuarioEntity());
        this.papelRepository = papelRepository;
    }

    @Override
    public UsuarioEntity equalProperties(UsuarioEntity entity, UsuarioRequest data) {
        String Nome = data.nome();
        if (Nome != null) {
            entity.setNome(Nome);
        }

        String senha = data.senha();
        if (senha != null) {
            entity.setSenha(senha);
        }

        PapelEntity papel = null;
        try {
            papel = papelRepository.findByNome(data.nomePapel())
                    .orElseThrow();
        } catch (ResponseStatusException ignored) {}
        if (papel != null) {
            entity.setPapel(papel);
        }

        return entity;
    }
}
