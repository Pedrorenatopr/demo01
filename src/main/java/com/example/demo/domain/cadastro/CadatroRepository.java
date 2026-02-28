package com.example.demo.domain.cadastro;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface CadatroRepository extends JpaRepository<cadastro, Long> {


    Page<cadastro> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Cadastro m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.cadastro.id from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    cadastro escolherCadastroAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);


    @Query("""
            select m.ativo
            from Cadastro m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
