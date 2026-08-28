package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.dtos.entitysDTO.ServicosRealizado;
import com.MaxBr221.GitHub.model.AtendimentoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AtendimentoServicoRepository extends JpaRepository<AtendimentoServico, Long> {
    boolean existsByIdAndAtendimentoProprietarioId(Long id, Long proprietarioId);
    Optional<AtendimentoServico> findByIdAndAtendimentoProprietarioId(Long id, Long proprietarioId);
    List<AtendimentoServico> findAllByAtendimentoProprietarioId(Long proprietarioId);

    @Query("""
    SELECT new com.MaxBr221.GitHub.dtos.entitysDTO.ServicosRealizado(
        s.nome,
        COUNT(ats.id)
    )
    FROM AtendimentoServico ats
    JOIN ats.servico s
    JOIN ats.atendimento a
    WHERE a.proprietario.id = :tenantId
      AND a.dataServico BETWEEN :inicio AND :fim
    GROUP BY s.nome
    ORDER BY COUNT(ats.id) DESC
""")
    List<ServicosRealizado> findServicoRealizado(
            @Param("tenantId") Long tenantId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}