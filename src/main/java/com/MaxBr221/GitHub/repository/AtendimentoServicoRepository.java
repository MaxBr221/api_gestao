package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.dtos.entitysDTO.ServicosRealizado;
import com.MaxBr221.GitHub.model.AtendimentoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoServicoRepository extends JpaRepository<AtendimentoServico, Long> {
    boolean existsById(Long id);

    @Query("""
                SELECT new com.MaxBr221.GitHub.dtos.entitysDTO.ServicosRealizado(
                    s.nome,
                    COUNT(ats.id)
                )
                FROM AtendimentoServico ats
                JOIN ats.servico s
                JOIN ats.atendimento a
                WHERE a.dataServico BETWEEN :inicio AND :fim
                GROUP BY s.nome
                ORDER BY COUNT(ats.id) DESC
            """)
    List<ServicosRealizado> findServicoRealizado(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}