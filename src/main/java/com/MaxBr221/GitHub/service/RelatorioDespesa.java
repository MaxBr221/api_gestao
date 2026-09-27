package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.RelatorioDespesaResponseDTO;
import com.MaxBr221.GitHub.model.Despesa;
import com.MaxBr221.GitHub.repository.DespesaRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioDespesa {

    private final DespesaRepository despesaRepository;
    private final ZoneId zone = ZoneId.of("America/Sao_Paulo");

    public RelatorioDespesaResponseDTO relatorioMensal(){
        Long tenantId = TenantContext.getTenantId();

        LocalDate hoje = LocalDate.now(zone);
        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        LocalDate ultimoDia = hoje.withDayOfMonth(hoje.lengthOfMonth());
        List<Despesa> despesa = despesaRepository.findAllByProprietarioId(tenantId);

        return montarRelatorio(despesa, primeiroDia, ultimoDia);

    }
    public RelatorioDespesaResponseDTO despesaAnual(){
        Long tenantId = TenantContext.getTenantId();

        LocalDate hoje = LocalDate.now(zone);
        LocalDate inicio = hoje.withDayOfMonth(1);
        LocalDate fim = hoje.withDayOfMonth(hoje.lengthOfMonth());
        List<Despesa> despesas = despesaRepository.findAllByProprietarioId(tenantId);

        return montarRelatorio(despesas, inicio, fim);

    }
    private RelatorioDespesaResponseDTO montarRelatorio(List<Despesa> despesas,
                                                        LocalDate inicio,
                                                        LocalDate fim){
        Long tenantId = TenantContext.getTenantId();
        BigDecimal valorDespesa = BigDecimal.ZERO;
        for(Despesa despesa: despesas){
            valorDespesa = valorDespesa.add(despesa.getValor());
        }
        List<Despesa> despesaList = despesaRepository.findByDataBetweenAndProprietarioId(inicio, fim, tenantId);
        String despesa =
                despesaList.isEmpty()
                        ? null
                        : despesaList.get(0).getCategoria().name();
        return new RelatorioDespesaResponseDTO(valorDespesa, despesa);
    }
}
