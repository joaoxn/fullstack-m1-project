package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.MateriaRequest;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.service.MateriaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materias")
@RequiredArgsConstructor
@Slf4j
public class MateriaController {
    private final MateriaServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<MateriaEntity> get(@PathVariable Long id) {
        log.info("GET /materias/{} -> Buscando matéria por id", id);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> get() {
        log.info("GET /materias -> Buscando todas as matérias");
        List<MateriaEntity> materias = service.getAll();
        log.debug("GET /materias -> Encontrado {} matérias", materias.size());
        return ResponseEntity.ok(materias);
    }

    @PostMapping
    public ResponseEntity<MateriaEntity> post(@RequestBody MateriaRequest materiaRequest) {
        log.info("POST /materias -> Criando nova matéria");
        log.debug("POST /materias -> Criando nova matéria com nome: {}", materiaRequest.nome());
        return ResponseEntity.ok(service.create(materiaRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<MateriaEntity> put(
            @PathVariable Long id,
            @RequestBody MateriaRequest materiaRequest
    ) {
        log.info("PUT /materias/{} -> Alterando matéria informada", id);
        log.debug("PUT /materias/{} -> Alterando matéria para o nome: {}", id, materiaRequest.nome());
        return ResponseEntity.ok(service.alter(id, materiaRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("DELETE /materias/{} -> Deletando matéria", id);
        return ResponseEntity.ok(service.delete(id));
    }
}
