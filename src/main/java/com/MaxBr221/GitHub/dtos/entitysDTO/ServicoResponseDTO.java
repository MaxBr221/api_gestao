package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.Servico;
import com.MaxBr221.GitHub.model.Status;

public record ServicoResponseDTO(
        Long id,
        String nome,
        double preco,
        Status status,
        String descricao
) {
    public ServicoResponseDTO(Servico servico){
        this(servico.getId(), servico.getNome(), servico.getPreco(), servico.getStatus(), servico.getDescricao());
    }
}