package com.example.demo.domain.secundarios;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CadastrosSecundariosRepository extends JpaRepository<CadastrosSecundarios, Long> {
    Page<CadastrosSecundarios> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p.ativo
            from CadastrosSecundarios p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
