package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.CategoriaDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResponseDTO(

        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        CategoriaDespesa categoria,
        String observacao

) {
}