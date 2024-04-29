package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.LoginRequest;
import com.fullstack.educacional.controller.dto.response.LoginResponse;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private final BCryptPasswordEncoder bCryptEncoder; // decifrar senhas
    private final JwtEncoder jwtEncoder; // codificar um JWT
    private final JwtDecoder jwtDecoder; // decodifica um JWT
    private final UsuarioRepository usuarioRepository;

    private final static long TEMPO_EXPIRACAO = 36000L; //contante de tempo de expiração em segundos

    public LoginResponse gerarToken(
            @RequestBody LoginRequest loginRequest
    ){
        String aaaa = loginRequest.login();
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByLogin(loginRequest.login()) // busca dados de usuario por loginUsuario
                .orElseThrow(                                  // caso usuario não exista gera um erro
                        ()->{
                            log.error("Erro, usuário não existe");
                            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário com login "+ loginRequest.login() +"não encontrado");
                        }
                );

        if(!usuarioEntity.senhaValida(loginRequest, bCryptEncoder)){ // valida se a senha recebida é a mesma que foi salva com BCrypt
            log.error("Senha incorreta");                      // caso senha não bata, gera um erro
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
        }

        Instant now = Instant.now();

        String scope = usuarioEntity.getPapel().getNome();

        JwtClaimsSet claims = JwtClaimsSet.builder() // Conjunto de campos do JWT, incluindo os campos pré-definidos e campos customizados
                .issuer("projeto1") // autor do token
                .issuedAt(now)      // momento da criação do token
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO)) // tempo de expiração
                .subject(usuarioEntity.getId().toString())   // sujeito do token ou dono do token
                .claim("scope", scope) // campo customizado, chamado scope que será adicionado ao token, alem dos campos anteriores
                .build(); // constroi o Objeto de JwtClaimsSet

        var valorJWT = jwtEncoder.encode(
                        JwtEncoderParameters.from(claims) // parametros para encode do token
                ) // token foi criado, porém está em uma classe que não tem o token puro, ele o token e várias coisas a mais
                .getTokenValue(); // pegar o valor do token em si

        return new LoginResponse(valorJWT, TEMPO_EXPIRACAO); // corpo de resposta é um objeto de LoginResponse


    }


    public String buscaCampo(String token, String claim) {
            token = token.substring(token.indexOf(' ')+1);
        return jwtDecoder
                .decode(token) // decifra o token
                .getClaims() // busca um campo especifico do token
                .get(claim)    // definindo o campo a ser buscado
                .toString(); // transforma a resposta em string
    }
}

