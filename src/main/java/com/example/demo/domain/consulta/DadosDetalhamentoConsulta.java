package com.example.demo.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idcadastro, Long idCadastrosSecundarios, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getCadastro().getId(), consulta.getCadastrosSecundarios().getId(), consulta.getData());
    }
}
