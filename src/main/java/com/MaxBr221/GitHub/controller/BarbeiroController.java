package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.BarbeiroRequestDTO;
import com.MaxBr221.GitHub.dtos.BarbeiroResponseDTO;
import com.MaxBr221.GitHub.model.Barbeiro;
import com.MaxBr221.GitHub.service.BarbeiroService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
@RequiredArgsConstructor
@Slf4j
public class BarbeiroController {
    private final BarbeiroService barbeiroService;

    @PostMapping
    public ResponseEntity<BarbeiroResponseDTO> create(@RequestBody BarbeiroRequestDTO barbeiroRequestDTO){
        BarbeiroResponseDTO barbeiro = barbeiroService.createBarbeiro(barbeiroRequestDTO);
        log.info("Barbeiro {} criado!", barbeiro.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(barbeiro);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        barbeiroService.delete(id);
        log.info("Apagando Barbeiro!");
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroResponseDTO> findById(@PathVariable Long id){
        BarbeiroResponseDTO barbeiro = barbeiroService.findById(id);
        log.info("Buscando barbeiro com id {}", barbeiro.id());
        return ResponseEntity.ok(barbeiro);
    }
    @GetMapping
    public ResponseEntity<List<BarbeiroResponseDTO>> findAll(){
        List<BarbeiroResponseDTO> barbeiros = barbeiroService.findAll();
        log.info("Listando barbeiros!");
        return ResponseEntity.ok(barbeiros);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroResponseDTO> update(@PathVariable Long id, @RequestBody BarbeiroRequestDTO barbeiroDTO){
        BarbeiroResponseDTO barbeiro = barbeiroService.update(id, barbeiroDTO);
        log.info("Editando barbeiro de id {}", barbeiro.id());
        return ResponseEntity.ok(barbeiro);
    }
}
