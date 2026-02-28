package com.example.demo.controller;

import com.example.demo.domain.cadastro.*;
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
@RequestMapping("usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuariosController {

    @Autowired
    private CadatroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastro dados, UriComponentsBuilder uriBilder) {
        var cadastro = new cadastro(dados);
        repository.save(cadastro);

        var uri = uriBilder.path("/ususarios/{id}").buildAndExpand(cadastro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCadastro(cadastro));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCadastro>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemCadastro::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacao dados) {
        var cadastro = repository.getReferenceById(dados.id());
        cadastro.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCadastro(cadastro));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var cadastro = repository.getReferenceById(id);
        cadastro.excluir();

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var cadastro = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCadastro(cadastro));
    }



}
