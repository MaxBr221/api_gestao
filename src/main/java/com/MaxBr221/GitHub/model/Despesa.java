package com.MaxBr221.GitHub.model;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "UPDATE despesa SET ativo = false WHERE id = ?")
@SQLRestriction("ativo = true")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaDespesa categoria;

    @Column(length = 500)
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Proprietario proprietario;

    private boolean ativo = true;
}