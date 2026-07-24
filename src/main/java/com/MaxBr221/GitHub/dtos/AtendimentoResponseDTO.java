package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.FormaPagamento;

import java.time.LocalDateTime;

public record AtendimentoResponseDTO(
        Long id,
        LocalDateTime dataServico,
        String barbeiro,
        String usuario,
        FormaPagamento formaPagamento,
        double valor,
        String observacao
) {
}