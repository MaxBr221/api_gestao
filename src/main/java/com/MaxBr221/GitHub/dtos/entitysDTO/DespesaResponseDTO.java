package com.MaxBr221.GitHub.dtos.entitysDTO;

import com.MaxBr221.GitHub.model.CategoriaDespesa;
import com.MaxBr221.GitHub.model.Despesa;

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
    public DespesaResponseDTO(Despesa despesa){
        this(despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                despesa.getData(),
                despesa.getCategoria(),
                despesa.getObservacao());
    }
}