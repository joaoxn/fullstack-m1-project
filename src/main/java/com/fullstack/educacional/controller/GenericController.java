package com.fullstack.educacional.controller;

import com.fullstack.educacional.service.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GenericController<E, DTO, S extends GenericService<E, DTO>> {
    private final S service;
    
    public GenericController(String controllerMapping, S service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<E> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<E>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<E> post(@RequestBody DTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<E> put(
            @PathVariable Long id,
            @RequestBody DTO request
    ) {
        return ResponseEntity.ok(service.alter(id, request));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
