package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class TestController {

    private GenericService<UsuarioEntity> service;

    public List<UsuarioEntity> get() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public UsuarioEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    public UsuarioEntity put(@PathVariable Long id, @RequestBody UsuarioEntity usuario) {
        return service.alter(id, usuario);
    }
}
