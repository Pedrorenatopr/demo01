package com.example.demo.domain.consulta;

import com.example.demo.domain.cadastro.cadastro;
import com.example.demo.domain.secundarios.CadastrosSecundarios;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cadastro_id")
    private cadastro cadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cadastrossecundarios_id")
    private CadastrosSecundarios cadastrosSecundarios;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }


}
