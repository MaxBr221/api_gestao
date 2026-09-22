package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Optional<Despesa> findByIdDespesaAndProprietarioId(Long id, Long proprietarioId);
    List<Despesa> findAllByDespensaProprietarioId(Long proprietarioId);
}
