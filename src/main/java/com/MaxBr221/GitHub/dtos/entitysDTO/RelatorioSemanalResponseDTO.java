package com.MaxBr221.GitHub.dtos.entitysDTO;

import java.math.BigDecimal;
import java.time.DayOfWeek;

public record RelatorioSemanalResponseDTO(
        DayOfWeek dia,
        BigDecimal faturamento
) {
}