package com.MaxBr221.GitHub.controller;


import com.MaxBr221.GitHub.service.ValorFinalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/valorFinal")
@RequiredArgsConstructor
@Slf4j
public class ValorFinalController {
    private ValorFinalService valorFinalService;

    @GetMapping("/mensal")
    public ResponseEntity<BigDecimal> valorFinalMensal(){
        BigDecimal valorMensal = valorFinalService.calculaValorMensal();
        log.info("Buscando valor final mensal!");
        return ResponseEntity.ok(valorMensal);
    }
    @GetMapping("/anual")
    public ResponseEntity<BigDecimal> valorFinalAnual(){
        BigDecimal valorAnual = valorFinalService.calculaValorAnual();
        log.info("Buscando valor final anual!");
        return ResponseEntity.ok(valorAnual);
    }
    @GetMapping("/semanal")
    public ResponseEntity<BigDecimal> valorFinalSemanal(){
        BigDecimal valorSemanal = valorFinalService.calculaValorSemanal();
        log.info("Buscando valor semanal!");
        return ResponseEntity.ok(valorSemanal);
    }
}
