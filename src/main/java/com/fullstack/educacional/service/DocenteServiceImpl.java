package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.DocenteRequest;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.DocenteRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class DocenteServiceImpl extends GenericServiceImpl<DocenteEntity, DocenteRequest, DocenteRepository> implements GenericService<DocenteEntity, DocenteRequest> {
    private final DocenteRepository repository;
    private final UsuarioRepository usuarioRepository;

    public DocenteServiceImpl(DocenteRepository repository, UsuarioRepository usuarioRepository) {
        super(repository, new DocenteEntity());
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public DocenteEntity create(DocenteRequest docenteRequest) {
        if (usuarioRepository.findByLogin(docenteRequest.login())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getPapel().getNome().equals("ALUNO")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tentou criar docente com um usuário com papel de ALUNO");
        }
        return repository.save(equalProperties(new DocenteEntity(), docenteRequest));
    }

    @Override
    public DocenteEntity equalProperties(DocenteEntity entity, DocenteRequest data) {
        String nome = data.nome();
        if (nome != null) {
            entity.setNome(nome);
        }

        UsuarioEntity usuario = null;
        try {
            usuario = usuarioRepository.findByLogin(data.login())
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
