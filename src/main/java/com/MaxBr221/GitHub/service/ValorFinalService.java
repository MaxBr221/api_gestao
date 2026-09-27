package com.MaxBr221.GitHub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ValorFinalService {
    private final RelatorioService relatorioService;
    private final RelatorioDespesa relatorioDespesa;

    public BigDecimal calculaValorMensal(){
        BigDecimal faturamento = relatorioService.relatorioMensal().faturamento();
        BigDecimal despesa = relatorioDespesa.relatorioMensal().valorDespesa();

        return faturamento.subtract(despesa);
    }
    public BigDecimal calculaValorAnual(){
        BigDecimal faturamento = relatorioService.relatorioAnual().faturamento();
        BigDecimal despesa = relatorioDespesa.despesaAnual().valorDespesa();

        return faturamento.subtract(despesa);
    }
}
