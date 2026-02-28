package com.example.demo.domain.secundarios;

import com.example.demo.domain.cadastro.Especialidade;

public record DadosListagemCadastrosSecundarios(Long id, String nome, String email, Especialidade especialidade) {

    public DadosListagemCadastrosSecundarios(CadastrosSecundarios cadastrosSecundarios) {

        this(cadastrosSecundarios.getId(), cadastrosSecundarios.getNome(), cadastrosSecundarios.getEmail(), cadastrosSecundarios.getEspecialidade());
    }
}
