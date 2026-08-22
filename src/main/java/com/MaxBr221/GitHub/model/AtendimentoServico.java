package com.MaxBr221.GitHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "atendimento_servico")
public class AtendimentoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    //recebe a chave estrangeira
    @JoinColumn(name = "atendimento_id")
    @JsonBackReference
    private Atendimento atendimento;
    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;
    @Column(nullable = false)
    private BigDecimal total;

}
