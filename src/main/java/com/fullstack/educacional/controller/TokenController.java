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

        LoginResponse response = tokenService.gerarToken(loginRequest);

        return ResponseEntity.ok( // Objeto usado para criar um corpo de resposta
                response // corpo de resposta é um objeto de LoginResponse
        );

    }

}