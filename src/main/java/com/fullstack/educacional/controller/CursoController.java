package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.CursoRequest;
import com.fullstack.educacional.datasource.entity.CursoEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.repository.CursoRepository;
import com.fullstack.educacional.service.CursoServiceImpl;
import com.fullstack.educacional.service.GenericServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cursos")
@RequiredArgsConstructor
public class CursoController {
    private CursoServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<CursoEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<CursoEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<CursoEntity> post(@RequestBody CursoRequest cursoRequest) {
        return ResponseEntity.ok(service.create(cursoRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<CursoEntity> put(
            @PathVariable Long id,
            @RequestBody CursoRequest cursoRequest
    ) {
        return ResponseEntity.ok(service.create(cursoRequest));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("{id}/materias")
    public ResponseEntity<List<MateriaEntity>> getAllMaterias(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllMaterias(id));
    }
}
