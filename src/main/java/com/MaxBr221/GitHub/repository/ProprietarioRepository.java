package com.MaxBr221.GitHub.repository;

import com.MaxBr221.GitHub.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    Optional<Proprietario> findByLogin(String login);
    Optional<Proprietario> findByLoginAndTenantId(String login, Long tenantId);
    Boolean existsByLogin(String login);
    Optional<Proprietario> findById(Long tenantId);
}