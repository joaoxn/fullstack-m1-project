package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.DocenteRequest;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.service.DocenteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("docentes")
@RequiredArgsConstructor
public class DocenteController {
    private final DocenteServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<DocenteEntity> get(Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<DocenteEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<DocenteEntity> post(@RequestBody DocenteRequest docenteRequest) {
        return ResponseEntity.ok(service.create(docenteRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<DocenteEntity> put(
            @PathVariable Long id,
            @RequestBody DocenteRequest docenteRequest
    ) {
        return ResponseEntity.ok(service.alter(id, docenteRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
