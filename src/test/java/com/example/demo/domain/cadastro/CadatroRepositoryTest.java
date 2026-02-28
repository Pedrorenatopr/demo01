package com.example.demo.domain.cadastro;

import com.example.demo.domain.consulta.Consulta;
import com.example.demo.domain.secundarios.CadastrosSecundarios;
import com.example.demo.domain.endereco.DadosEndereco; // Ajuste os imports conforme seus pacotes
import com.example.demo.domain.secundarios.DadosCadastroSecundarios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CadatroRepositoryTest {

    @Autowired
    private CadatroRepository cadatroRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico profissional cadastrado nao esta disponivel na data")
    void escolherCadastroAleatorioLivreNaDataCenario1() {
        // Arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var cadastro = cadastrarCadastro("Pedro", "pedro@email.com", "61999999999", Especialidade.GESTOR);
        var cadastrosSecundarios = cadastrarSecundario("João", "joao@email.com", "61888888888");

        // Cadastramos uma consulta para o Pedro neste horário, logo ele NÃO está livre
        cadastrarConsulta(cadastro, cadastrosSecundarios, proximaSegundaAs10);

        // Act
        var cadastroLivre = cadatroRepository.escolherCadastroAleatorioLivreNaData(Especialidade.GESTOR, proximaSegundaAs10);

        // Assert
        assertThat(cadastroLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver cadastro quando ele estiver disponivel na data")
    void escolherCadastroAleatorioLivreNaDataCenario2() {
        // Arrange
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var cadastro = cadastrarCadastro("Pedro", "pedro@email.com", "61999999999", Especialidade.GESTOR);


        // Act
        var cadastroLivre = cadatroRepository.escolherCadastroAleatorioLivreNaData(Especialidade.GESTOR, proximaSegundaAs10);

        // Assert
        assertThat(cadastroLivre).isEqualTo(cadastro);
    }

    // =========================================================================================
    // MÉTODOS PRIVADOS AUXILIARES (FACTORY METHODS)
    // =========================================================================================

    private void cadastrarConsulta(cadastro cadastro, CadastrosSecundarios cadastrosSecundarios, LocalDateTime data) {
        em.persist(new Consulta(null, cadastro, cadastrosSecundarios, data, null));
    }

    private cadastro cadastrarCadastro(String nome, String email, String telefone, Especialidade especialidade) {
        // Assumindo que sua entidade Cadastro recebe um DTO no construtor
        var cadastro = new cadastro(dadosCadastro(nome, email, telefone, especialidade));
        em.persist(cadastro);
        return cadastro;
    }

    private CadastrosSecundarios cadastrarSecundario(String nome, String email, String telefone) {
        // Assumindo que sua entidade CadastrosSecundarios recebe um DTO no construtor
        var secundario = new CadastrosSecundarios(dadosSecundario(nome, email, telefone));
        em.persist(secundario);
        return secundario;
    }

    private DadosCadastro dadosCadastro(String nome, String email, String telefone, Especialidade especialidade) {
        // Ajuste a ordem dos parâmetros conforme o seu Record/Classe DTO
        return new DadosCadastro(
                "123",
                nome,
                email,
                telefone,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroSecundarios dadosSecundario(String nome, String email, String telefone) {
        // Ajuste a ordem dos parâmetros conforme o seu Record/Classe DTO
        return new DadosCadastroSecundarios(
                nome,
                email,
                telefone,
                dadosEndereco(),
                Especialidade.PROGRAMADOR // Default para secundário, ajuste se necessário
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua Teste",
                "Bairro Teste",
                "70000000",
                "Brasília",
                "DF",
                null,
                "10"
        );
    }
}
