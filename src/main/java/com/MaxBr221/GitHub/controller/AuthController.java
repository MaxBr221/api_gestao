package com.MaxBr221.GitHub.controller;

import com.MaxBr221.GitHub.dtos.authDTO.Cadastro;
import com.MaxBr221.GitHub.dtos.authDTO.DadosTokenJWT;
import com.MaxBr221.GitHub.dtos.authDTO.Login;
import com.MaxBr221.GitHub.dtos.entitysDTO.ProprietarioResponseDTO;
import com.MaxBr221.GitHub.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/cadastro")
    public ResponseEntity<ProprietarioResponseDTO> cadastro(@RequestBody Cadastro cadastro){
        ProprietarioResponseDTO dto = authService.cadastrarProprietario(cadastro);
        log.info("Cadastrando Proprietario!");
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody Login login) throws HttpMessageNotReadableException {
        DadosTokenJWT token = authService.login(login);
        log.info("Proprietario {} logando", login.login());
        return ResponseEntity.ok(token);
    }

}
