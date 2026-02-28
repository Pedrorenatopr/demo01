package com.example.demo.domain.consulta;

import com.example.demo.domain.cadastro.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idcadastro,

        @NotNull
        Long idCadastrosSecundarios,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
