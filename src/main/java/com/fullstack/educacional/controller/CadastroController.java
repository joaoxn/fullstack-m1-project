package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.UsuarioRequest;
import com.fullstack.educacional.controller.dto.response.UsuarioResponse;
import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.repository.PapelRepository;
import com.fullstack.educacional.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CadastroController {
    private final UsuarioServiceImpl usuarioService;
    private final PapelRepository papelRepository;

    @PostMapping("cadastro")
    public ResponseEntity<String> novoUsuario(
            @Validated @RequestBody UsuarioRequest usuarioRequest
    ) {

        usuarioService.create(usuarioRequest);

        return ResponseEntity.ok("Usuario Salvo!");
    }

    @PostMapping("papel")
    public ResponseEntity<String> papel(@RequestBody PapelEntity papel) {
        papelRepository.save(papel);
        return ResponseEntity.ok("success");
    }

    @GetMapping("usuarios")
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllResponse());
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getResponse(id));
    }

    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.ok("Usuário "+ id +" deletado com sucesso!");
    }
}