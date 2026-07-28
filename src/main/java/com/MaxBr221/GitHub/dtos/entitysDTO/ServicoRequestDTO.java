package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.Status;

import java.math.BigDecimal;

public record ServicoRequestDTO(
        String nome,
        BigDecimal preco,
        Status status,
        String descricao
) {
}