package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.InserirUsuarioRequest;
import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.PapelRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<UsuarioEntity, UsuarioRepository> implements GenericService<UsuarioEntity> {

    private final BCryptPasswordEncoder bCryptEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;

    public UsuarioServiceImpl(
            UsuarioRepository repository,
            BCryptPasswordEncoder bCryptEncoder,
            UsuarioRepository usuarioRepository,
            PapelRepository papelRepository
    ) {
        super(repository);
        this.bCryptEncoder = bCryptEncoder;
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
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

    public void cadastraNovoUsuario(
            @RequestBody InserirUsuarioRequest inserirUsuarioRequest
    ) {
        boolean usuarioExsite = usuarioRepository
                .findByNome(inserirUsuarioRequest.nomeUsuario())
                .isPresent(); // retorna um true se a entidade procurada existir, caso o contrário, retorna false

        if (usuarioExsite) {
            throw new RuntimeException("Usuario já existe");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(inserirUsuarioRequest.nomeUsuario());
        usuario.setSenha(
                bCryptEncoder.encode(inserirUsuarioRequest.senha()) // codificar a senha
        );
        usuario.setPapel(
                papelRepository.findByNome(inserirUsuarioRequest.nomePapel())
                        .orElseThrow(() -> new RuntimeException("Perfil inválido ou inexistente"))
        );

        usuarioRepository.save(usuario);
    }
}
