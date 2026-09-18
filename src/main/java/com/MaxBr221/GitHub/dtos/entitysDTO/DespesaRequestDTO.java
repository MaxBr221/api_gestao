package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.CategoriaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaRequestDTO(

        @NotBlank
        String descricao,

        @NotNull
        @Positive
        BigDecimal valor,

        @NotNull
        LocalDate data,

        @NotNull
        CategoriaDespesa categoria,

        String observacao

) {
}