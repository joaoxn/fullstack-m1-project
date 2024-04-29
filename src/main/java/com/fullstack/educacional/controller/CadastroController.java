package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.UsuarioRequest;
import com.fullstack.educacional.datasource.entity.PapelEntity;
import com.fullstack.educacional.datasource.entity.UsuarioEntity;
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
        return ResponseEntity.ok("Usuário Salvo! ID do usuário: "+ usuarioService.create(usuarioRequest).getId());
    }

    @PostMapping("papel")
    public ResponseEntity<String> papel(@RequestBody PapelEntity papel) {
        papelRepository.save(papel);
        return ResponseEntity.ok("Papel salvo com sucesso!");
    }

    @GetMapping("usuarios")
    public ResponseEntity<List<UsuarioEntity>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<UsuarioEntity> getUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.get(id));
    }

    @PutMapping("usuarios/senha")
    public ResponseEntity<String> alterUsuarioSenha(@RequestHeader(name = "Authorization") String token, @RequestBody String senha) {
        return ResponseEntity.ok(usuarioService.alterSenha(token, senha));
    }

    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }
}