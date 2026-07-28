package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    boolean existsByDataServico(LocalDateTime data);
    List<Atendimento> findByDataServicoBetween(LocalDateTime inicio, LocalDateTime fim);

}
