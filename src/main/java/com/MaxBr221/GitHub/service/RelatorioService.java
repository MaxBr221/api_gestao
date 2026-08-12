package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.relatorioDTO.RelatorioResponseDTO;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Atendimento;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import com.MaxBr221.GitHub.repository.AtendimentoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final AtendimentoServicoRepository atendimentoServicoRepository;
    private final AtendimentoRepository atendimentoRepository;

    public RelatorioResponseDTO relatorioDiario(){

        LocalDate hoje = LocalDate.now();

        LocalDateTime inicio = hoje.atStartOfDay();
        LocalDateTime fim = hoje.atTime(LocalTime.MAX);

        List<Atendimento> atendimentosDoDia = atendimentoRepository.findByDataServicoBetween(inicio, fim);

        return montarRelatorio(atendimentosDoDia, inicio, fim);
    }

    public RelatorioResponseDTO relatorioMensal(){
        LocalDate hoje = LocalDate.now();

        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        LocalDate ultimoDia = hoje.withDayOfMonth(hoje.lengthOfMonth());
        List<Atendimento> atendimentosDoMes = atendimentoRepository.findByDataServicoBetween(
                primeiroDia.atStartOfDay(), ultimoDia.atTime(LocalTime.MAX));

        return montarRelatorio(atendimentosDoMes, primeiroDia.atStartOfDay(), ultimoDia.atTime(LocalTime.MAX));
    }
    public RelatorioResponseDTO relatorioAnual(){
        
        LocalDate hoje = LocalDate.now();

        LocalDate primeiroDia = hoje.withDayOfMonth(1);
        LocalDate ultimoDia = hoje.withDayOfYear(hoje.lengthOfYear());

        List<Atendimento> atendimentosDoMes = atendimentoRepository.findByDataServicoBetween(
                primeiroDia.atStartOfDay(),
                ultimoDia.atTime(LocalTime.MAX));

        return montarRelatorio(atendimentosDoMes, primeiroDia.atStartOfDay(), ultimoDia.atTime(LocalTime.MAX));
    }

    private RelatorioResponseDTO montarRelatorio(List<Atendimento> atendimentos, LocalDateTime incio, LocalDateTime fim){
        int contAtendimentos = 0;
        BigDecimal faturamentoRelatorio = BigDecimal.ZERO;
        for(Atendimento a: atendimentos){
            faturamentoRelatorio = faturamentoRelatorio.add(a.getValor());
            contAtendimentos ++;
        }
        String atendimentoMaisFrequente = atendimentoServicoRepository.findServicoMaisRealizado(incio, fim);
        return new RelatorioResponseDTO(faturamentoRelatorio, contAtendimentos, atendimentoMaisFrequente);
    }


}
