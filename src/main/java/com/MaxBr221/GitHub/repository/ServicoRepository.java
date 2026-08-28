package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    boolean existsByNomeAndProprietarioId(String nome, Long proprietarioId);
    Optional<Servico> findByIdAndProprietarioId(Long id, Long proprietarioId);
    List<Servico> findAllByIdInAndProprietarioId(
            List<Long> ids,
            Long proprietarioId
    );
    List<Servico> findAllByProprietarioId(Long proprietarioId);
}

