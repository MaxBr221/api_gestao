package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoResponseDTO;
import com.MaxBr221.GitHub.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/atendimento")
@RequiredArgsConstructor
@Slf4j
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> create(@RequestBody AtendimentoRequestDTO atendimentoRequestDTO){
        AtendimentoResponseDTO atendimento = atendimentoService.create(atendimentoRequestDTO);
        log.info("Atendimento criado com id {}", atendimento.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        atendimentoService.delete(id);
        log.info("Atendimento deletado.");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> findById(@PathVariable Long id){
        AtendimentoResponseDTO atendimento = atendimentoService.findById(id);
        log.info("Buscando atendimento {}", atendimento.id());
        return ResponseEntity.ok(atendimento);
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> findAll(){
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.findAll();
        log.info("Listando atendimentos.");
        return ResponseEntity.ok(atendimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> update(@PathVariable Long id,
                                                         @RequestBody AtendimentoRequestDTO atendimentoRequestDTO){
        AtendimentoResponseDTO atendimento = atendimentoService.update(id, atendimentoRequestDTO);
        log.info("Atendimento {} atualizado.", atendimento.id());
        return ResponseEntity.ok(atendimento);
    }
    @GetMapping("/data")
    public ResponseEntity<List<AtendimentoResponseDTO>> listAtendimentos(@RequestParam LocalDate data){
        List<AtendimentoResponseDTO> atendimentos = atendimentoService.listarAtendimentos(data);
        log.info("Listando atendimentos da data: {}", data);
        return ResponseEntity.ok(atendimentos);
    }
}