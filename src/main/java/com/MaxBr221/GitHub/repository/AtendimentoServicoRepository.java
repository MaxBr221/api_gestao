package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.AtendimentoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AtendimentoServicoRepository extends JpaRepository<AtendimentoServico, Long> {
    boolean existsById(Long id);
    @Query("""
    SELECT ats.servico.nome
    FROM AtendimentoServico ats
    WHERE ats.atendimento.dataServico BETWEEN :inicio AND :fim
    GROUP BY ats.servico.nome
    ORDER BY COUNT(ats) DESC
    """)
    String findServicoMaisRealizado(
            LocalDateTime inicio,
            LocalDateTime fim);
}
