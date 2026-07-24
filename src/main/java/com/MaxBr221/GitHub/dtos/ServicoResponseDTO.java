package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.Status;

public record ServicoResponseDTO(
        Long id,
        String nome,
        double preco,
        Status status,
        String descricao
) {
}