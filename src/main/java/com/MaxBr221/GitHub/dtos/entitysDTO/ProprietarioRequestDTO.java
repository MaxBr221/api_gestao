package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.Role;

public record ProprietarioRequestDTO(
        String nome,
        String login,
        String senha,
        String telefone,
        Role role
) {

}