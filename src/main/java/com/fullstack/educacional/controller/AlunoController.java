package com.fullstack.educacional.controller;

import com.fullstack.educacional.controller.dto.request.AlunoRequest;
import com.fullstack.educacional.controller.dto.response.PontuacaoResponse;
import com.fullstack.educacional.datasource.entity.AlunoEntity;
import com.fullstack.educacional.datasource.entity.NotaEntity;
import com.fullstack.educacional.service.AlunoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alunos")
@RequiredArgsConstructor
@Slf4j
public class AlunoController {
    private final AlunoServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> get(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        log.info("GET /alunos/{} -> Buscando aluno", id);
        log.debug("GET /alunos/{} -> Buscando aluno para usuário de token: {}", id, token);
        return ResponseEntity.ok(service.get(id, token));
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> get() {
        log.info("GET /alunos -> Buscando todos os alunos");
        List<AlunoEntity> alunos = service.getAll();
        log.info("GET /alunos -> {} alunos encontrados", alunos.size());
        StringBuilder ids = new StringBuilder();
        for (AlunoEntity aluno : alunos) {
            ids.append(aluno.getId()).append(" ");
        }
        log.debug("GET /alunos -> {} alunos encontrados com IDs:\n{}", alunos.size(), ids);
        return ResponseEntity.ok(alunos);
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> post(@RequestBody AlunoRequest alunoRequest) {
        log.info("POST /alunos -> Criando um novo aluno");
        log.trace("POST /alunos -> Criando aluno: {}", alunoRequest);
        return ResponseEntity.ok(service.create(alunoRequest));
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> put(@PathVariable Long id, @RequestBody AlunoRequest alunoRequest) {
        log.info("PUT /alunos/{} -> Alterando dados do aluno", id);
        log.trace("PUT /alunos/{} -> Alterando dados do aluno de id: {}\nNovos dados: {}",id, id, alunoRequest);
        return ResponseEntity.ok(service.alter(id, alunoRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("DELETE /alunos/{} -> Deletando aluno", id);
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("{id}/notas")
    public ResponseEntity<List<NotaEntity>> getAllNotas(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        log.info("GET /alunos/{}/notas -> Buscando todas as notas do aluno", id);
        log.debug("GET /alunos/{}/notas -> Buscando as notas para usuário de token: {}", id, token);
        return ResponseEntity.ok(service.getAllNotas(id, token));
    }

    @GetMapping("{id}/pontuacao")
    public ResponseEntity<PontuacaoResponse> getPontuacaoTotal(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        log.info("GET /alunos/{}/pontuacao -> Buscando pontuação total do aluno", id);
        log.debug("GET /alunos/{}/pontuacao -> Buscando pontuação para usuário de token: {}", id, token);
        return ResponseEntity.ok(service.getPontuacaoTotal(id, token));
    }
}
