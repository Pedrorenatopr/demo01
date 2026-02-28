package com.example.demo.domain.consulta.validacoes;

import com.example.demo.domain.ValidacaoExeption;
import com.example.demo.domain.consulta.DadosAgendamentoConsulta;
import com.example.demo.domain.secundarios.CadastrosSecundariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCadastrosSecundariosAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private CadastrosSecundariosRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var cadastrosSecundariosAtivo = repository.findAtivoById(dados.idCadastrosSecundarios());
        if (cadastrosSecundariosAtivo == null || !cadastrosSecundariosAtivo) {
            throw new ValidacaoExeption("Consulta não pode ser agendada com cadastro inativo ou inexistente");
        }

    }

}
