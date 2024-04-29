package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.LoginRequest;
import com.fullstack.educacional.controller.dto.response.LoginResponse;
import com.fullstack.educacional.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> gerarToken(@RequestBody LoginRequest loginRequest) {
        log.info("POST /login -> Gerando token para o login informado");
        log.debug("POST /login -> Gerando token com login: {}", loginRequest.login());
        LoginResponse response = tokenService.gerarToken(loginRequest);
        log.info("POST /login -> Token gerado");
        log.debug("POST /login -> Token gerado com tempo de expiração (em segundos): {}", response.tempoExpiracao());
        return ResponseEntity.ok(response);
    }
}