package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.NotaRequest;
import com.fullstack.educacional.datasource.entity.NotaEntity;
import com.fullstack.educacional.service.NotaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notas")
@RequiredArgsConstructor
public class NotaController {
    private final NotaServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<NotaEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<NotaEntity> post(@RequestBody NotaRequest request, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(service.create(request, token));
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> put(
            @PathVariable Long id,
            @RequestBody NotaRequest request
    ) {
        return ResponseEntity.ok(service.alter(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
