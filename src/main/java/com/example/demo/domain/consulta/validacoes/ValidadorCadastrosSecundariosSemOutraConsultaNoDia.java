package com.example.demo.domain.consulta.validacoes;

import com.example.demo.domain.ValidacaoExeption;
import com.example.demo.domain.consulta.ConsultaRepository;
import com.example.demo.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCadastrosSecundariosSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var cadastrosSecundariosPossuiOutraConsultaNoDia = repository.existsByCadastrosSecundariosIdAndDataBetween(dados.idCadastrosSecundarios(), primeiroHorario, ultimoHorario);
        if (cadastrosSecundariosPossuiOutraConsultaNoDia) {
            throw new ValidacaoExeption("Cadastro Secundario ja possui uma consulta agendada nesse dia");
        }


    }
}
