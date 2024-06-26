package com.fullstack.educacional.service;

import com.fullstack.educacional.controller.dto.request.LoginRequest;
import com.fullstack.educacional.controller.dto.response.LoginResponse;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import com.fullstack.educacional.datasource.repository.UsuarioRepository;
import com.fullstack.educacional.infra.exception.CustomErrorException;
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

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final UsuarioRepository usuarioRepository;

    private final static long TEMPO_EXPIRACAO = 36000L; // Expiração em segundos

    public LoginResponse gerarToken(
            @RequestBody LoginRequest loginRequest
    ) {
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByLogin(loginRequest.login())
                .orElseThrow(
                        () -> {
                            log.error("Erro, usuário não existe");
                            return new CustomErrorException(HttpStatus.NOT_FOUND, "Usuário com login " + loginRequest.login() + " não encontrado");
                        }
                );

        if (!usuarioEntity.senhaValida(loginRequest, bCryptEncoder)) { // valida se a senha recebida
            log.error("Senha incorreta");
            throw new CustomErrorException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
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

        var valorJWT = jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();

        return new LoginResponse(valorJWT, TEMPO_EXPIRACAO);
    }

    public String buscaCampo(String token, String claim) {
        log.info("Método chamado: buscaCampo({}, {})", token, claim);
        token = token.substring(token.indexOf(' ') + 1);
        return jwtDecoder
                .decode(token) // decifra o token
                .getClaims() // busca um campo especifico do token
                .get(claim)    // definindo o campo a ser buscado
                .toString(); // transforma a resposta em string
    }
}

