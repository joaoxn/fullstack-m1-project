package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.TurmaRequest;
import com.fullstack.educacional.datasource.entity.TurmaEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("turmas")
public class TurmaController {
    private TurmaServiceImpl service;

    @PostMapping
    public TurmaEntity post(@RequestBody TurmaRequest turmaData) {
        return service.create(turmaData);
    }
}
