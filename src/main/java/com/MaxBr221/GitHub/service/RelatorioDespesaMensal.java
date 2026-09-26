package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.DespesaResponseDTO;
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
public class RelatorioDespesaMensal {
    private final DespesaService despesaService;
    private final DespesaRepository despesaRepository;

    public DespesaResponseDTO relatorioMensal(){
        Long tenantId = TenantContext.getTenantId();
        ZoneId zone = ZoneId.of("America/Sao_Paulo");

        LocalDate hoje = LocalDate.now(zone);
        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        LocalDate ultimoDia = hoje.withDayOfMonth(hoje.lengthOfMonth());
        List<Despesa> despesa = despesaRepository.findAllByProprietarioId(tenantId);

        return montarRelatorio(despesa, primeiroDia, ultimoDia);

    }
    private DespesaResponseDTO montarRelatorio(List<Despesa> despesas, LocalDate inicio, LocalDate fim){
        int contDespesa = 0;
        BigDecimal valorDespesa = BigDecimal.ZERO;
        for(Despesa despesa: despesas){
            valorDespesa = valorDespesa.add(despesa.getValor());
            contDespesa++;
        }
        return null;

    }


}
