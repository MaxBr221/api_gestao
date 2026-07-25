package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.AtendimentoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoServicoRepository extends JpaRepository<AtendimentoServico, Long> {
    boolean existsById(Long id);
}
