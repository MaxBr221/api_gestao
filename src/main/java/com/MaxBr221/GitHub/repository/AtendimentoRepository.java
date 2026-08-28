package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    List<Atendimento> findByProprietarioIdAndDataServicoBetween(Long proprietarioId,
                                                                LocalDateTime inicio,
                                                                LocalDateTime fim);

    Optional<Atendimento> findByIdAtendimentoAndProprietarioId(Long idAtendimento, Long proprietarioId);
    List<Atendimento> findAllByProprietarioId(Long proprietarioId);
}
