package com.example.demo.domain.cadastro;

import com.example.demo.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
