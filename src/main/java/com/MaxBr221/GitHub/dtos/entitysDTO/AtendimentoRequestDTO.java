package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.FormaPagamento;

import java.util.List;

public record AtendimentoRequestDTO(
        Long usuarioId,
        FormaPagamento formaPagamento,
        String observacao,
        List<Long> servicosIds
) {
}