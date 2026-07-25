package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.Role;

public record BarbeiroRequestDTO(
        String nome,
        String telefone,
        Role role
) {
}