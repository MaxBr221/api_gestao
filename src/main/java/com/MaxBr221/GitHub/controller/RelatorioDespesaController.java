package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.entitysDTO.RelatorioDespesaResponseDTO;
import com.MaxBr221.GitHub.service.RelatorioDespesa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/despesa")
@RequiredArgsConstructor
@Slf4j
public class RelatorioDespesaController {

    private final RelatorioDespesa relatorioDespesa;

    @GetMapping("/mensal")
    public ResponseEntity<RelatorioDespesaResponseDTO> relatorioMensal(){
        RelatorioDespesaResponseDTO relatorioDTO = relatorioDespesa.relatorioMensal();
        log.info("Buscando relatorio despesa mensal!");
        return ResponseEntity.ok(relatorioDTO);
    }
    @GetMapping("/anual")
    public ResponseEntity<RelatorioDespesaResponseDTO> relatorioAnual(){
        RelatorioDespesaResponseDTO relatorioDTO = relatorioDespesa.despesaAnual();
        log.info("Buscando relatorio despesa anual!");
        return ResponseEntity.ok(relatorioDTO);
    }
    @GetMapping("/semanal")
    public ResponseEntity<RelatorioDespesaResponseDTO> relatorioSemanal(){
        RelatorioDespesaResponseDTO relatorioDTO = relatorioDespesa.despesaSemanal();
        log.info("Buscando relatorio despesa semanal!");
        return ResponseEntity.ok(relatorioDTO);
    }

}
