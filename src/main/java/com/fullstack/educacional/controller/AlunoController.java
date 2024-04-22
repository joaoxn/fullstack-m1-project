package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.response.PontuacaoResponse;
import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.NotaEntity;
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
    public ResponseEntity<AlunoEntity> post(@RequestBody AlunoEntity alunoRequest) {
        return ResponseEntity.ok(service.create(alunoRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> put(@PathVariable Long id, @RequestBody AlunoEntity alunoRequest) {
        return ResponseEntity.ok(service.alter(id, alunoRequest));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("{id}/notas")
    public ResponseEntity<List<NotaEntity>> getAllNotas(@PathVariable Long id) {
        return service.getAllNotas(id);
    }

    @GetMapping("{id}/pontuacao")
    public ResponseEntity<PontuacaoResponse> getPontuacaoTotal(@PathVariable Long id) {
        return service.getPontuacaoTotal(id);
    }
}
