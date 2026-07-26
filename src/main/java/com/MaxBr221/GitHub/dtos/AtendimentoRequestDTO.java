package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.FormaPagamento;

import java.time.LocalDateTime;

public record AtendimentoRequestDTO(
        Long barbeiroId,
        Long usuarioId,
        FormaPagamento formaPagamento,
        String observacao,
        LocalDateTime data
) {
}