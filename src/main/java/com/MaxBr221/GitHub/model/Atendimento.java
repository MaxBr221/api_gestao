package com.MaxBr221.GitHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtendimento;
    @Column(name = "data", nullable = false)
    private LocalDateTime dataServico;
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    private Proprietario proprietario;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @Column(nullable = false)
    private BigDecimal valor;
    private String observacao;
    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
    private List<AtendimentoServico> atendimentos;



}
