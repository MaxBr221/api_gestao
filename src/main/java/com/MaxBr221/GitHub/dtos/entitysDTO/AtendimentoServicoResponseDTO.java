package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.AtendimentoServico;
import com.MaxBr221.GitHub.model.Servico;

import java.math.BigDecimal;

public record AtendimentoServicoResponseDTO(
        Long id,
        Servico servico,
        BigDecimal total
) {
    public AtendimentoServicoResponseDTO(AtendimentoServico atendimentoServico){
        this(atendimentoServico.getId(), atendimentoServico.getServico(), atendimentoServico.getTotal());
    }
}