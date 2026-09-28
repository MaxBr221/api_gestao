package com.MaxBr221.GitHub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/despesa")
@RequiredArgsConstructor
@Slf4j
public class RelatorioDespesaController {
    private final RelatorioDespesaController relatorioDespesaController;

}
