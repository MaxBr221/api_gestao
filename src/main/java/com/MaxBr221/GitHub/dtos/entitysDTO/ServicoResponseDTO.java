package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.Servico;

import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        String descricao
) {
    public ServicoResponseDTO(Servico servico){
        this(servico.getId(), servico.getNome(), servico.getPreco(), servico.getDescricao());
    }
}