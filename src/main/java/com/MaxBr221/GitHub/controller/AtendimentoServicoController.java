package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.AtendimentoServicoRequestDTO;
import com.MaxBr221.GitHub.dtos.AtendimentoServicoResponseDTO;
import com.MaxBr221.GitHub.service.AtendimentoServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendimento-servico")
@RequiredArgsConstructor
@Slf4j
public class AtendimentoServicoController {

    private final AtendimentoServicoService atendimentoServicoService;

    @PostMapping
    public ResponseEntity<AtendimentoServicoResponseDTO> create(
            @RequestBody AtendimentoServicoRequestDTO dto){

        AtendimentoServicoResponseDTO atendimentoServico = atendimentoServicoService.create(dto);
        log.info("Serviço adicionado ao atendimento.");
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoServico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        atendimentoServicoService.delete(id);
        log.info("Serviço removido do atendimento.");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoServicoResponseDTO> findById(@PathVariable Long id){
        AtendimentoServicoResponseDTO atendimentoServico = atendimentoServicoService.findById(id);
        log.info("Buscando AtendimentoServico {}", atendimentoServico.id());
        return ResponseEntity.ok(atendimentoServico);
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoServicoResponseDTO>> findAll(){
        List<AtendimentoServicoResponseDTO> lista = atendimentoServicoService.findAll();
        log.info("Listando AtendimentoServico.");
        return ResponseEntity.ok(lista);
    }
}