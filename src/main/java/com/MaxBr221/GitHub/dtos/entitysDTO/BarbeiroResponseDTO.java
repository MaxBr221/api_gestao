package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.Barbeiro;

public record BarbeiroResponseDTO(
        Long id,
        String nome,
        String telefone
) {
    public BarbeiroResponseDTO(Barbeiro barbeiro){
        this(barbeiro.getId(), barbeiro.getNome(), barbeiro.getTelefone());
    }
}