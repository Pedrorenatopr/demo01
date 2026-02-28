package com.example.demo.controller;

import com.example.demo.domain.secundarios.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuariossecundarios")
@SecurityRequirement(name = "bearer-key")
public class UsuariosSecundariosController {

    @Autowired
    private CadastrosSecundariosRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroSecundarios dados, UriComponentsBuilder uriBilder) {
        var CadastrosSecundarios = new CadastrosSecundarios(dados);
        repository.save(new CadastrosSecundarios(dados));

        var uri = uriBilder.path("/usuariossecundarios/{id}").buildAndExpand(CadastrosSecundarios.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCadastrosSecundarios (CadastrosSecundarios));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemCadastrosSecundarios>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCadastrosSecundarios::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCadastroSecundarios dados) {
        var CadastrosSecundarios = repository.getReferenceById(dados.id());
        CadastrosSecundarios.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCadastrosSecundarios (CadastrosSecundarios));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var CadastrosSecundarios = repository.getReferenceById(id);
        CadastrosSecundarios.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var CadastrosSecundarios = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCadastrosSecundarios (CadastrosSecundarios));
    }




}
