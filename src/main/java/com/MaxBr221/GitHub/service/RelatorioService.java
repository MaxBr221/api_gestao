package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.RelatorioSemanalResponseDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.ServicosRealizado;
import com.MaxBr221.GitHub.dtos.relatorioDTO.RelatorioResponseDTO;
import com.MaxBr221.GitHub.model.Atendimento;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import com.MaxBr221.GitHub.repository.AtendimentoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<RelatorioSemanalResponseDTO> relatorioSemanal(){
        LocalDate hoje = LocalDate.now();
        LocalDate segunda = hoje.with(
                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
        );
        LocalDate domingo = segunda.plusDays(6);

        return faturamentoSemanal(segunda, domingo);
    }

    private List<RelatorioSemanalResponseDTO> faturamentoSemanal(LocalDate segunda, LocalDate domingo){

        List<Atendimento> atendimentoSemanais = atendimentoRepository.findByDataServicoBetween(
                segunda.atStartOfDay(), domingo.atTime(LocalTime.MAX));

        Map<DayOfWeek, BigDecimal> faturamentoPorDia = atendimentoSemanais.stream()
                .collect(Collectors.groupingBy(
                        atendimento -> atendimento.getDataServico().getDayOfWeek(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Atendimento::getValor,
                                BigDecimal::add
                        )
                ));
        List<RelatorioSemanalResponseDTO> relatorios = Arrays.stream(DayOfWeek.values())
                .map(dia -> new RelatorioSemanalResponseDTO(
                        dia, faturamentoPorDia.getOrDefault(dia, BigDecimal.ZERO)))
                .toList();
        return relatorios;
    }

    private RelatorioResponseDTO montarRelatorio(List<Atendimento> atendimentos, LocalDateTime incio, LocalDateTime fim){
        int contAtendimentos = 0;
        BigDecimal faturamentoRelatorio = BigDecimal.ZERO;
        for(Atendimento a: atendimentos){
            faturamentoRelatorio = faturamentoRelatorio.add(a.getValor());
            contAtendimentos ++;
        }
        List<ServicosRealizado> servicos =
                atendimentoServicoRepository.findServicoRealizado(incio, fim);

        String servicoMaisRealizado =
                servicos.isEmpty()
                        ? null
                        : servicos.get(0).nome();
        return new RelatorioResponseDTO(faturamentoRelatorio, contAtendimentos, servicoMaisRealizado);
    }
    public List<ServicosRealizado> servicosRealizadoHoje(){
        LocalDate hoje = LocalDate.now();

        LocalDateTime inicio = hoje.atStartOfDay();
        LocalDateTime fim = hoje.atTime(LocalTime.MAX);

        return atendimentoServicoRepository.findServicoRealizado(inicio, fim);
    }


}
