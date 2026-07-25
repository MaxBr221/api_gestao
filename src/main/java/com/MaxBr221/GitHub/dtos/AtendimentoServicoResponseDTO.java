package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.AtendimentoServico;
import com.MaxBr221.GitHub.model.Servico;

public record AtendimentoServicoResponseDTO(
        Long id,
        Servico servico,
        double total
) {
    public AtendimentoServicoResponseDTO(AtendimentoServico atendimentoServico){
        this(atendimentoServico.getId(), atendimentoServico.getServico(), atendimentoServico.getTotal());
    }
}