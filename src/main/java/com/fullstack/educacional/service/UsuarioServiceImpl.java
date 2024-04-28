package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.UsuarioRequest;
import com.fullstack.educacional.controller.dto.response.UsuarioResponse;
import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.PapelRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<UsuarioEntity, UsuarioRequest, UsuarioRepository> implements GenericService<UsuarioEntity, UsuarioRequest> {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final PapelRepository papelRepository;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public UsuarioServiceImpl(UsuarioRepository repository, PapelRepository papelRepository, BCryptPasswordEncoder bCryptEncoder, TokenService tokenService) {
        super(repository, new UsuarioEntity());
        this.papelRepository = papelRepository;
        this.bCryptEncoder = bCryptEncoder;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    public UsuarioEntity create(UsuarioRequest usuarioRequest) {
        if(repository.findByLogin(usuarioRequest.login()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário com login "+ usuarioRequest.login() +" já existe!");
        }
        return repository.save(equalProperties(new UsuarioEntity(), usuarioRequest));
    }

    public UsuarioResponse getResponse(Long id) {
        UsuarioEntity usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
        return new UsuarioResponse(usuario.getId(), usuario.getLogin(), usuario.getPapel());
    }

    public List<UsuarioResponse> getAllResponse() {
        List<UsuarioEntity> usuarios = repository.findAll();
        List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
        for(UsuarioEntity usuario : usuarios) {
            UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getLogin(), usuario.getPapel());
            usuarioResponseList.add(usuarioResponse);
        }
        return usuarioResponseList;
    }

    public UsuarioEntity alterSenha(String token, String senha) {
        Long id = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );
        UsuarioEntity usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado usuário com id: "+ id));
        usuario.setSenha(senha);
        return repository.save(usuario);
    }

    public UsuarioEntity equalProperties(UsuarioEntity entity, UsuarioRequest data) {
        String login = data.login();
        if (login != null) {
            entity.setLogin(login);
        }

        String senha = bCryptEncoder.encode(data.senha());
        if (senha != null) {
            entity.setSenha(senha);
        }

        PapelEntity papel = null;
        try {
            papel = papelRepository.findByNome(data.papel())
                    .orElseThrow();
        } catch (ResponseStatusException ignored) {}
        if (papel != null) {
            entity.setPapel(papel);
        }

        return entity;
    }
}
