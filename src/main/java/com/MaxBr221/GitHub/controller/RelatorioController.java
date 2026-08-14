package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.entitysDTO.RelatorioSemanalResponseDTO;
import com.MaxBr221.GitHub.dtos.relatorioDTO.RelatorioResponseDTO;
import com.MaxBr221.GitHub.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
@Slf4j
public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping("/diario")
    public ResponseEntity<RelatorioResponseDTO> relatorioDiario(){
        RelatorioResponseDTO relatorioResponseDTO = relatorioService.relatorioDiario();
        log.info("Listando relatorio diario!");
        return ResponseEntity.ok(relatorioResponseDTO);
    }
    @GetMapping("/mensal")
    public ResponseEntity<RelatorioResponseDTO> relatorioMensal(){
        RelatorioResponseDTO relatorioResponseDTO = relatorioService.relatorioMensal();
        log.info("Listando relatorio mensal!");
        return ResponseEntity.ok(relatorioResponseDTO);
    }
    @GetMapping("/anual")
    public ResponseEntity<RelatorioResponseDTO> relatorioAnual(){
        RelatorioResponseDTO relatorioResponseDTO = relatorioService.relatorioAnual();
        log.info("Listando relatorio anual!");
        return ResponseEntity.ok(relatorioResponseDTO);
    }
    @GetMapping("/semanal")
    public ResponseEntity<List<RelatorioSemanalResponseDTO>> relatoriosemanal(){
        List<RelatorioSemanalResponseDTO> relatorioSemanalDTO = relatorioService.relatorioSemanal();
        log.info("Listando relatorio semanal!");
        return ResponseEntity.ok(relatorioSemanalDTO);
    }

}
