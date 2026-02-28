package com.example.demo.domain.secundarios;

import com.example.demo.domain.cadastro.Especialidade;
import com.example.demo.domain.endereco.Endereco;

public record DadosDetalhamentoCadastrosSecundarios(Long id, String nome, String email, String telefone, Especialidade especialidade, Endereco endereco) {
    public DadosDetalhamentoCadastrosSecundarios(CadastrosSecundarios cadastrosSecundarios) {
        this(cadastrosSecundarios.getId(), cadastrosSecundarios.getNome(), cadastrosSecundarios.getEmail(), cadastrosSecundarios.getTelefone(), cadastrosSecundarios.getEspecialidade(), cadastrosSecundarios.getEndereco());
    }
}
