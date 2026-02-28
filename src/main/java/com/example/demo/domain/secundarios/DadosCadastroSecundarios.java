package com.example.demo.domain.secundarios;

import com.example.demo.domain.cadastro.Especialidade;
import com.example.demo.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroSecundarios(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotNull
        String telefone,

        @NotNull
        @Valid
        DadosEndereco endereco,

        @NotNull
        Especialidade especialidade) {

}
