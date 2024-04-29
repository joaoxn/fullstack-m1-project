package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.CursoRequest;
import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.service.CursoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cursos")
@RequiredArgsConstructor
@Slf4j
public class CursoController {
    private final CursoServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<CursoEntity> get(@PathVariable Long id) {
        log.info("GET /cursos/{} -> Buscando curso de id ({})", id, id);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<CursoEntity>> get() {
        log.info("GET /cursos -> Buscando todos os cursos");
        List<CursoEntity> cursos = service.getAll();
        log.debug("GET /cursos -> Encontrado {} cursos", cursos.size());
        return ResponseEntity.ok(cursos);
    }

    @PostMapping
    public ResponseEntity<CursoEntity> post(@RequestBody CursoRequest cursoRequest) {
        log.info("POST /cursos -> Criando novo curso");
        return ResponseEntity.ok(service.create(cursoRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<CursoEntity> put(
            @PathVariable Long id,
            @RequestBody CursoRequest cursoRequest
    ) {
        log.info("PUT /cursos/{} -> Alterando curso de id ({})", id, id);
        log.debug("PUT /cursos/{} -> Alterando curso para: {}", id, cursoRequest);
        return ResponseEntity.ok(service.alter(id, cursoRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("DELETE /cursos/{} -> Deletando curso de id ({})", id, id);
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("{id}/materias")
    public ResponseEntity<List<MateriaEntity>> getAllMaterias(@PathVariable Long id) {
        log.info("GET /cursos/{}/materias -> Buscando todas as matérias do curso informado", id);
        List<MateriaEntity> materias = service.getAllMaterias(id);
        log.debug("GET /cursos/{}/materias -> Encontrado {} matérias do curso informado", id, materias.size());
        return ResponseEntity.ok(materias);
    }
}
