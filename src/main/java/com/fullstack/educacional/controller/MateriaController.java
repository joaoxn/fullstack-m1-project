package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.MateriaRequest;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.entity.MateriaEntity;
import com.fullstack.educacional.datasource.repository.MateriaRepository;
import com.fullstack.educacional.service.GenericService;
import com.fullstack.educacional.service.GenericServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materias")
@RequiredArgsConstructor
public class MateriaController {

    private MateriaService service;

    @GetMapping("{id}")
    public ResponseEntity<MateriaEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<MateriaEntity> post(@RequestBody MateriaRequest materiaRequest) {
        return ResponseEntity.ok(service.create(materiaRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<MateriaEntity> put(
            @PathVariable Long id,
            @RequestBody MateriaRequest materiaRequest
    ) {
        return ResponseEntity.ok(service.create(materiaRequest));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
