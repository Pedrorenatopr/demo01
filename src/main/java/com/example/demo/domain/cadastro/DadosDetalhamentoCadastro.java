package com.example.demo.domain.cadastro;

import com.example.demo.domain.endereco.Endereco;

public record DadosDetalhamentoCadastro(Long id, String nome, String email, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoCadastro(cadastro cadastro) {
        this(cadastro.getId(), cadastro.getNome(), cadastro.getEmail(), cadastro.getTelefone(), cadastro.getEspecialidade(),cadastro.getEndereco());
    }
}
