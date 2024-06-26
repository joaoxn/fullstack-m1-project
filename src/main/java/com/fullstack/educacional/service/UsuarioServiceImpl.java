package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.UsuarioRequest;
import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.PapelRepository;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import com.fullstack.educacional.infra.exception.CustomErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsuarioServiceImpl extends GenericServiceImpl<UsuarioEntity, UsuarioRequest, UsuarioRepository> implements GenericService<UsuarioEntity, UsuarioRequest> {
    private final BCryptPasswordEncoder bCryptEncoder;
    private final PapelRepository papelRepository;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    public UsuarioServiceImpl(UsuarioRepository repository, PapelRepository papelRepository, BCryptPasswordEncoder bCryptEncoder, TokenService tokenService) {
        super(repository);
        this.papelRepository = papelRepository;
        this.bCryptEncoder = bCryptEncoder;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public UsuarioEntity newEntity() {
        return new UsuarioEntity();
    }

    public UsuarioEntity create(UsuarioRequest usuarioRequest) {
        validarUsuario(usuarioRequest, "todos");
        UsuarioEntity usuario = equalProperties(new UsuarioEntity(), usuarioRequest);
        return repository.save(usuario);
    }

    @Override
    public UsuarioEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(
                        HttpStatus.NOT_FOUND, "Entidade não encontrada com ID: " + id));
    }

    public String alterSenha(String token, String senha) {
        Long id = Long.valueOf(
                tokenService.buscaCampo(token, "sub")
        );
        UsuarioEntity usuario = repository.findById(id)
                .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "Não encontrado usuário com id: "+ id));
        UsuarioRequest request = new UsuarioRequest(null, senha, null);
        validarUsuario(request, "senha");
        usuario = equalProperties(usuario, request);
        repository.save(usuario);
        return "Senha do usuário com login \""+ usuario.getLogin() +"\" alterada com sucesso!";
    }

    public UsuarioEntity equalProperties(UsuarioEntity entity, UsuarioRequest data) {
        String login = data.login();
        if (login != null) {
            entity.setLogin(login);
        } else if (entity.getLogin() == null){
            log.warn("{}.equalProperties() -> retornando usuário com 'login' nulo", getClass());
        }

        String senha = bCryptEncoder.encode(data.senha());
        if (senha != null) {
            entity.setSenha(senha);
        } else if (entity.getLogin() == null){
            log.warn("{}.equalProperties() -> retornando usuário com 'senha' nulo", getClass());
        }

        PapelEntity papel = null;
        try {
            papel = papelRepository.findByNome(data.papel())
                    .orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND));
        } catch (RuntimeException ignore) {}
        if (papel != null) {
            entity.setPapel(papel);
        } else if (entity.getLogin() == null){
            log.warn("{}.equalProperties() -> retornando usuário com 'papel' nulo", getClass());
        }

        return entity;
    }

    public void validarUsuario(UsuarioRequest usuario, String opcao) {
        switch(opcao) {
            case "login":
            if (usuario.login().length() < 3) {
                throw new CustomErrorException(HttpStatus.BAD_REQUEST,
                        "Login do usuário é muito curto! Tamanho: " + usuario.login().length() + " caracteres (Mínimo: 3)");
            }
            break;
            case "senha":
            if (usuario.senha().length() < 3) {
                throw new CustomErrorException(
                        HttpStatus.BAD_REQUEST,
                        "Senha do usuário é muito curta! Tamanho: " + usuario.senha().length() + " caracteres (Mínimo: 3)"
                );
            }
            break;
            case "jaExiste":
            if (repository.findByLogin(usuario.login()).isPresent()) {
                throw new CustomErrorException(
                        HttpStatus.BAD_REQUEST,
                        "Usuário com login " + usuario.login() + " já existe!"
                );
            }
            break;
            default:
                validarUsuario(usuario, "login");
                validarUsuario(usuario, "senha");
                validarUsuario(usuario, "jaExiste");
        }
    }
}
