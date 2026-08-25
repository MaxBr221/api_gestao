package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.entitysDTO.ProprietarioRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.ProprietarioResponseDTO;
import com.MaxBr221.GitHub.service.ProprietarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proprietario")
@RequiredArgsConstructor
@Slf4j
public class ProprietarioController {
    private final ProprietarioService proprietarioService;

    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioResponseDTO> findById(@PathVariable Long id, @RequestBody ProprietarioRequestDTO proprietarioDTO){
        ProprietarioResponseDTO proprietario = proprietarioService.update(id, proprietarioDTO);
        log.info("Atualizando proprietário!");
        return ResponseEntity.ok(proprietario);
    }
    @PostMapping
    public ResponseEntity<Void> mudarSenha(@RequestBody String login, @RequestBody String novaSenha){
        proprietarioService.mudarSenha(login, novaSenha);
        log.info("Atualizando senha de {}!", login);
        return ResponseEntity.noContent().build();
    }
}
