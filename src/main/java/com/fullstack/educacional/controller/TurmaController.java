package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.TurmaRequest;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import com.fullstack.educacional.datasource.repository.TurmaRepository;
import com.fullstack.educacional.service.GenericServiceImpl;
import com.fullstack.educacional.service.TurmaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turmas")
@RequiredArgsConstructor
public class TurmaController {
    private final TurmaServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<TurmaEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<TurmaEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<TurmaEntity> post(@RequestBody TurmaRequest turmaData) {
        return ResponseEntity.ok(service.create(turmaData));
    }

    @PutMapping("{id}")
    public ResponseEntity<TurmaEntity> put(
            @PathVariable Long id,
            @RequestBody TurmaRequest turmaData
    ) {
        return ResponseEntity.ok(service.alter(id, turmaData));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
