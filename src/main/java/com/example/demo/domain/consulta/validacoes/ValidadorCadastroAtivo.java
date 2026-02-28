package com.example.demo.domain.consulta.validacoes;

import com.example.demo.domain.ValidacaoExeption;
import com.example.demo.domain.cadastro.CadatroRepository;
import com.example.demo.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCadastroAtivo  implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private CadatroRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idcadastro() == null) {
            return;
        }

        boolean cadastroEstaAtivo = repository.findAtivoById(dados.idcadastro());
        if(!cadastroEstaAtivo) {
            throw new ValidacaoExeption("Consulta nao pode ser agendada com cadastro excluido");
        }
    }


    }
