package com.fullstack.educacional.controller;

import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.DocenteEntity;
import com.fullstack.educacional.service.DocenteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("docentes")
public class DocenteController {
    private DocenteServiceImpl service;

    @GetMapping("${id}")
    public ResponseEntity<DocenteEntity> get(Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<DocenteEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<DocenteEntity> post(@RequestBody DocenteEntity docente) {
        return ResponseEntity.ok(service.create(docente));
    }

    @PutMapping("${id}")
    public ResponseEntity<DocenteEntity> put(@PathVariable Long id, @RequestBody DocenteEntity data) {
        return ResponseEntity.ok(service.alter(id, data));
    }

    @DeleteMapping("${id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
