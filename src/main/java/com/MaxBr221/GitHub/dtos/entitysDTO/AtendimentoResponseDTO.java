package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AtendimentoResponseDTO(
        Long id,
        LocalDateTime dataServico,
        Proprietario proprietario,
        FormaPagamento formaPagamento,
        BigDecimal valor,
        String observacao,
        List<AtendimentoServico> atendimentos
) {
    public AtendimentoResponseDTO(Atendimento atendimento){
        this(atendimento.getIdAtendimento(), atendimento.getDataServico(),
                atendimento.getProprietario(),
                atendimento.getFormaPagamento(),
                atendimento.getValor(),
                atendimento.getObservacao(),
                atendimento.getAtendimentos());
    }
}