package com.MaxBr221.GitHub.dtos.entitysDTO;

import java.math.BigDecimal;

public record RelatorioResponseDTO(BigDecimal faturamento, int quantAtendimentos, String servicoMaiorFrequencia) {
}
