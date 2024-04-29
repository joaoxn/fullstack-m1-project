package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.TurmaRequest;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.service.TurmaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turmas")
@RequiredArgsConstructor
@Slf4j
public class TurmaController {
    private final TurmaServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<TurmaEntity> get(@PathVariable Long id) {
        log.info("GET /turmas/{} -> Buscando turma por id", id);
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<TurmaEntity>> get() {
        log.info("GET /turmas -> Buscando todas as turmas");
        List<TurmaEntity> turmas = service.getAll();
        log.debug("GET /turmas -> Encontrado {} turmas", turmas.size());
        return ResponseEntity.ok(turmas);
    }

    @PostMapping
    public ResponseEntity<TurmaEntity> post(@RequestBody TurmaRequest turmaData) {
        log.info("POST /turmas -> Criando turma com nome: {}", turmaData.nome());
        log.info("POST /turmas -> Criando turma:\nID do Curso: {},\nID do Docente: {}", turmaData.cursoId(), turmaData.docenteId());
        return ResponseEntity.ok(service.create(turmaData));
    }

    @PutMapping("{id}")
    public ResponseEntity<TurmaEntity> put(
            @PathVariable Long id,
            @RequestBody TurmaRequest turmaData
    ) {
        log.info("PUT /turmas/{} -> Alterando turma por id", id);
        log.debug("PUT /turmas/{} -> Alterando turma por id:\nNome: {},\nID do Curso: {},\nID do Docente: {}",
                id, turmaData.nome(), turmaData.cursoId(), turmaData.docenteId());
        return ResponseEntity.ok(service.alter(id, turmaData));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("DELETE /turmas/{} -> Deletando turma por id", id);
        return ResponseEntity.ok(service.delete(id));
    }
}
