package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.NotaRequest;
import com.fullstack.educacional.datasource.entity.NotaEntity;
import com.fullstack.educacional.service.NotaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notas")
@RequiredArgsConstructor
@Slf4j
public class NotaController {
    private final NotaServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> get(@PathVariable Long id) {
        log.info("GET /notas/{} -> Buscando nota por id", id);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<NotaEntity>> get() {
        log.info("GET /notas -> Buscando todas as notas");
        List<NotaEntity> notas = service.getAll();
        log.debug("GET /notas -> Encontrado {} notas", notas.size());
        return ResponseEntity.ok(notas);
    }

    @PostMapping
    public ResponseEntity<NotaEntity> post(@RequestBody NotaRequest request, @RequestHeader(name = "Authorization") String token) {
        log.info("POST /notas -> Criando nota");
        log.debug("POST /notas -> Criando nota:\nValor: {},\nID do Aluno: {},\nID da Matéria: {}",
                request.valor(), request.alunoId(), request.materiaId());
        log.trace("POST /notas -> Criando nota por usuário com token: {}", token);
        return ResponseEntity.ok(service.create(request, token));
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> put(
            @PathVariable Long id,
            @RequestBody NotaRequest request
    ) {
        log.info("PUT /notas/{} -> Alterando nota por id", id);
        log.debug("PUT /notas/{} -> Alterando nota\nNovo valor: {}", id, request.valor());
        return ResponseEntity.ok(service.alter(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("DELETE /notas/{} -> Deletando nota por id", id);
        return ResponseEntity.ok(service.delete(id));
    }
}
