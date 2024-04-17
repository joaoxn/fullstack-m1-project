package com.fullstack.educacional.service;

import com.fullstack.educacional.datasource.entity.UsuarioEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestMain {

    private GenericService<UsuarioEntity> service;

    @GetMapping("{id}")
    public UsuarioEntity get(@PathVariable Long id) {
        return service.get(id);
    }
}
