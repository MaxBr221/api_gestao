package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.entitysDTO.ServicoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.ServicoResponseDTO;
import com.MaxBr221.GitHub.service.ServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
@Slf4j
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> create(@RequestBody ServicoRequestDTO servicoRequestDTO){
        ServicoResponseDTO servico = servicoService.create(servicoRequestDTO);
        log.info("Serviço {} criado!", servico.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        servicoService.delete(id);
        log.info("Serviço deletado!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id){
        ServicoResponseDTO servico = servicoService.findById(id);
        log.info("Buscando serviço de id {}", servico.id());
        return ResponseEntity.ok(servico);
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> findAll(){
        List<ServicoResponseDTO> servicos = servicoService.findAll();
        log.info("Listando serviços.");
        return ResponseEntity.ok(servicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody ServicoRequestDTO servicoRequestDTO){
        ServicoResponseDTO servico = servicoService.update(id, servicoRequestDTO);
        log.info("Serviço {} atualizado.", servico.id());
        return ResponseEntity.ok(servico);
    }
    @GetMapping("/maisRealizado")
    public ResponseEntity<String> maisRealizado(){
        var servicoMaisRealizado = servicoService.servicoMaisRealizado();
        log.info("Exibindo Servico mais realizado, {}", servicoMaisRealizado);
        return ResponseEntity.ok(servicoMaisRealizado);
    }
}