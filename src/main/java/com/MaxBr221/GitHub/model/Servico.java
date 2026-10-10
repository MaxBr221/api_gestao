package com.MaxBr221.GitHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE servico SET ativo = false WHERE id = ?")
@SQLRestriction("ativo = true")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private BigDecimal preco;
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Proprietario proprietario;

    private boolean ativo = true;

}
