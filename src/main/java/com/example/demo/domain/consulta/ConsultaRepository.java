package com.example.demo.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


    boolean existsByCadastroIdAndData(Long idcadastro, LocalDateTime data);

    boolean existsByCadastrosSecundariosIdAndDataBetween(Long idcadastro, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
