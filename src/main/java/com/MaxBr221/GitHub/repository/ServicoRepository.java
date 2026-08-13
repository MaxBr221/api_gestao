package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Optional<Servico> findByNome(String nome);
    boolean existsByNome(String nome);
    @Query("""
    SELECT s.nome, COUNT(ats)
    FROM AtendimentoServico ats
    JOIN ats.servico s
    GROUP BY s.nome
    ORDER BY COUNT(ats) DESC
""")
    List<Object[]> servicosMaisRealizados();


}
