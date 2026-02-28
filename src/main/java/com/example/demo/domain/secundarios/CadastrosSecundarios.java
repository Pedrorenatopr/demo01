package com.example.demo.domain.secundarios;

import com.example.demo.domain.cadastro.Especialidade;
import com.example.demo.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cadastrossecundarios")
@Entity(name = "CadastrosSecundarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CadastrosSecundarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private  String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private Endereco endereco;

    private Boolean ativo;



    public CadastrosSecundarios (DadosCadastroSecundarios dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());


    }


    public void atualizarInformacoes(@Valid DadosAtualizacaoCadastroSecundarios dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes (dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
