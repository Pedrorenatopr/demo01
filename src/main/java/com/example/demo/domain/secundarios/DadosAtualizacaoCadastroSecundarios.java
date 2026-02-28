package com.example.demo.domain.secundarios;

import com.example.demo.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCadastroSecundarios(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
