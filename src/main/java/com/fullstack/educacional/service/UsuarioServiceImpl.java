package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<UsuarioEntity, UsuarioRepository> implements GenericService<UsuarioEntity> {

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super(repository);
    }

    @Override
    public UsuarioEntity equalProperties(UsuarioEntity entity, UsuarioEntity data) {
        String Nome = data.getNome();
        if (Nome != null) {
            entity.setNome(Nome);
        }

        String login = data.getLogin();
        if (login != null) {
            entity.setLogin(login);
        }

        String senha = data.getSenha();
        if (senha != null) {
            entity.setSenha(senha);
        }

        PapelEntity papel = data.getPapel();
        if (papel != null) {
            entity.setPapel(papel);
        }

        return entity;
    }
}
