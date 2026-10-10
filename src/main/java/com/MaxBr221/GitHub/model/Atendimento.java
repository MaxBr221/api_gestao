package com.MaxBr221.GitHub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE atendimento SET ativo = false WHERE id_atendimento = ?")
@SQLRestriction("ativo = true")
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
    @JsonManagedReference
    private List<AtendimentoServico> atendimentos;

    private boolean ativo = true;



}
