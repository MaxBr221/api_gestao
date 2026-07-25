package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    boolean existsByData(LocalDateTime data);
}
