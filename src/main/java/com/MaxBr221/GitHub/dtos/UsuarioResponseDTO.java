package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.Role;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String login,
        String telefone,
        Role role
) {
}