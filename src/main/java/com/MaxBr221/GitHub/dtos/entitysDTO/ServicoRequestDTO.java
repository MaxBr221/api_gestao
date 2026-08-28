package com.MaxBr221.GitHub.dtos.entitysDTO;

import java.math.BigDecimal;

public record ServicoRequestDTO(
        String nome,
        BigDecimal preco,
        String descricao
) {
}