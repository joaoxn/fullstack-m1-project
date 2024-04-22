package com.fullstack.educacional.controller;

import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.service.AlunoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alunos")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> post(@RequestBody AlunoEntity aluno) {
        return ResponseEntity.ok(service.create(aluno));
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> put(@PathVariable Long id, @RequestBody AlunoEntity data) {
        return ResponseEntity.ok(service.alter(id, data));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
