package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.Status;

public record ServicoRequestDTO(
        String nome,
        double preco,
        Status status,
        String descricao
) {
}