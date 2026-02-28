package com.example.demo.domain.consulta;

import com.example.demo.domain.ValidacaoExeption;
import com.example.demo.domain.cadastro.CadatroRepository;
import com.example.demo.domain.cadastro.cadastro;
import com.example.demo.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import com.example.demo.domain.consulta.validacoes.ValidadorCancelamentoDeConsulta;
import com.example.demo.domain.secundarios.CadastrosSecundarios;
import com.example.demo.domain.secundarios.CadastrosSecundariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private CadatroRepository cadatroRepository;

    @Autowired
    private CadastrosSecundariosRepository cadastrosSecundariosRepository;


    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!cadastrosSecundariosRepository.existsById(dados.idCadastrosSecundarios())) {
            throw new ValidacaoExeption("Id do cadastro secundario nao existe");
        }

        if (dados.idcadastro() != null && !cadatroRepository.existsById(dados.idcadastro())) {
            throw new ValidacaoExeption("Id do cadastro nao existe");
        }

        validadores.forEach(v -> v.validar(dados));


        var cadastro = escolherCadastro(dados);
        var CadastrosSecundarios = cadastrosSecundariosRepository.getReferenceById(dados.idCadastrosSecundarios());
        if (cadastro == null) {
            throw new ValidacaoExeption("Nao existe cadastro disponivel nessa data!");
        }
        var consulta = new Consulta(null, cadastro, CadastrosSecundarios, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    public void  cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoExeption("Id da consulta informado nao existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());

    }

    private cadastro escolherCadastro(DadosAgendamentoConsulta dados) {
        if (dados.idcadastro() != null) {
            return cadatroRepository.getReferenceById(dados.idcadastro());
        }

        if (dados.especialidade() == null) {
            throw  new ValidacaoExeption("Especialidade e obrigatoria quando o cadastro nao for escolhido!");
        }

        return cadatroRepository.escolherCadastroAleatorioLivreNaData(dados.especialidade(), dados.data());

    }







}
