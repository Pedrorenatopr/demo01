package com.example.demo.domain.cadastro;

public record DadosListagemCadastro(Long id, String nome, String email, Especialidade especialidade) {

    public DadosListagemCadastro(cadastro cadastro) {

        this(cadastro.getId(), cadastro.getNome(), cadastro.getEmail(), cadastro.getEspecialidade());

    }


}
