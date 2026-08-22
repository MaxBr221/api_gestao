package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    boolean existsByNome(String nome);

}
