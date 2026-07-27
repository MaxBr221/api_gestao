package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.*;

import java.time.LocalDateTime;
import java.util.List;

public record AtendimentoResponseDTO(
        Long id,
        LocalDateTime dataServico,
        Barbeiro barbeiro,
        Proprietario proprietario,
        FormaPagamento formaPagamento,
        double valor,
        String observacao,
        List<AtendimentoServico> atendimentos
) {
    public AtendimentoResponseDTO(Atendimento atendimento){
        this(atendimento.getIdAtendimento(), atendimento.getDataServico(),
                atendimento.getBarbeiro(),
                atendimento.getProprietario(),
                atendimento.getFormaPagamento(),
                atendimento.getValor(),
                atendimento.getObservacao(),
                atendimento.getAtendimentos());
    }
}