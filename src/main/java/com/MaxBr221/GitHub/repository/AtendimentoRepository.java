package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    boolean existsByData(LocalDateTime data);
    List<Atendimento> findByDataServicoBetween(LocalDateTime inicio, LocalDateTime fim);
        @Query("""
    SELECT ats.servico.nome
    FROM atendimento_servico ats
    WHERE ats.atendimento.dataServico BETWEEN :inicio AND :fim
    GROUP BY ats.servico.nome
    ORDER BY COUNT(ats) DESC
    """)
    String findServicoMaisRealizado(
            LocalDateTime inicio,
            LocalDateTime fim);
}
