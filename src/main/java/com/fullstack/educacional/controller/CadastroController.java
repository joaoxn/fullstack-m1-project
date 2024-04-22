package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.UsuarioRequest;
import com.fullstack.educacional.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastroController {
    private final UsuarioServiceImpl usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> novoUsuario(
            @Validated @RequestBody UsuarioRequest usuarioRequest
    ) {

        usuarioService.cadastraNovoUsuario(usuarioRequest);

        return ResponseEntity.ok("Usuario Salvo!");
    }

}