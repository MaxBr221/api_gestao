package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    boolean existsByTelefone(String telefone);
}
