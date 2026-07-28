package com.MaxBr221.GitHub.dtos.relatorioDTO;

import java.math.BigDecimal;

public record RelatorioResponseDTO(BigDecimal faturamento, int quantAtendimentos, String servicoMaiorFrequencia) {
}
