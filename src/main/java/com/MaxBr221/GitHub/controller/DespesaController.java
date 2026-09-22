package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.entitysDTO.DespesaRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.DespesaResponseDTO;
import com.MaxBr221.GitHub.service.DespesaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
@Slf4j
public class DespesaController {
    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody DespesaRequestDTO despesaRequestDTO){
        despesaService.criarDespesa(despesaRequestDTO);
        log.info("Adicionando despesa!");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> findById(@PathVariable Long id){
        DespesaResponseDTO despesaDTO = despesaService.findById(id);
        log.info("Buscando despesa {}", id);
        return ResponseEntity.ok(despesaDTO);
    }
    @GetMapping
    public ResponseEntity<List<DespesaResponseDTO>> findAll(){
        List<DespesaResponseDTO> despesaResponseDTOList = despesaService.findAll();
        log.info("Listando todas as despesas!");
        return ResponseEntity.ok(despesaResponseDTOList);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        despesaService.delete(id);
        log.info("Deletando despesa {}", id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> update(@PathVariable Long id, @RequestBody DespesaRequestDTO despesaRequestDTO){
        DespesaResponseDTO despesaDTO = despesaService.update(id, despesaRequestDTO);
        log.info("Editando despesa do proprietario {}", despesaDTO.id());
        return ResponseEntity.ok(despesaDTO);
    }
}
