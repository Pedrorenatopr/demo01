package com.example.demo.domain.consulta.validacoes;

import com.example.demo.domain.ValidacaoExeption;
import com.example.demo.domain.consulta.ConsultaRepository;
import com.example.demo.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCadastroComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var cadastroPossuiOutraConsultaNoMesmoHorario = repository.existsByCadastroIdAndData(dados.idcadastro(), dados.data());
        if (cadastroPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoExeption("Cadastro ja possui outra consulta agendada nesse mesmo horario");
        }
    }

}
