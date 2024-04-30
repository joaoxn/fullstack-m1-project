package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.DocenteRequest;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.service.DocenteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("docentes")
@RequiredArgsConstructor
@Slf4j
public class DocenteController {
    private final DocenteServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<DocenteEntity> get(Long id) {
        log.info("GET /docentes/{} -> Buscando docente de id: {}", id, id);
        DocenteEntity docente = service.get(id);
        log.debug("GET /docentes/{} -> Encontrou docente de nome: {}", id, docente.getNome());
        return ResponseEntity.ok(docente);
    }

    @GetMapping
    public ResponseEntity<List<DocenteEntity>> get() {
        log.info("GET /docentes -> Buscando todos os docentes");
        List<DocenteEntity> docentes = service.getAll();
        log.debug("GET /docentes -> Encontrado {} docentes", docentes.size());
        return ResponseEntity.ok(docentes);
    }

    @PostMapping
    public ResponseEntity<DocenteEntity> post(@RequestBody DocenteRequest docenteRequest) {
        log.info("POST /docentes -> Criando docente");
        log.debug("POST /docentes -> Criando docente \"{}\" com login: {}", docenteRequest.nome(), docenteRequest.login());
        return ResponseEntity.ok(service.create(docenteRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<DocenteEntity> put(
            @PathVariable Long id,
            @RequestBody DocenteRequest docenteRequest
    ) {
        log.info("PUT /docentes/{} -> Alterando docente", id);
        log.debug("PUT /docentes/{} -> Alterando docente para os dados: {}", id, docenteRequest);
        return ResponseEntity.ok(service.alter(id, docenteRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("DELETE /docentes/{} -> Deletando docente", id);
        return ResponseEntity.ok(service.delete(id));
    }
}
