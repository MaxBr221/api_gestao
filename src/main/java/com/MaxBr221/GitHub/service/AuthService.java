package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.config.infra.TokenService;
import com.MaxBr221.GitHub.dtos.authDTO.Cadastro;
import com.MaxBr221.GitHub.dtos.authDTO.DadosTokenJWT;
import com.MaxBr221.GitHub.dtos.authDTO.Login;
import com.MaxBr221.GitHub.dtos.entitysDTO.ProprietarioResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.UnauthorizedException;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.model.Role;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProprietarioRepository proprietarioRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public ProprietarioResponseDTO cadastrarProprietario(Cadastro cadastro) {
        if (proprietarioRepository.existsByLogin(cadastro.login())) {
            throw new EventFullException("Proprietário já cadastrado!");
        }
        String senhaCriptografada = passwordEncoder.encode(cadastro.senha());
        Proprietario novoProprietario = new Proprietario(cadastro.nome(), cadastro.login(), senhaCriptografada, cadastro.telefone(), Role.ADMIN);
        proprietarioRepository.save(novoProprietario);
        return new ProprietarioResponseDTO(novoProprietario);
    }
    public DadosTokenJWT login(Login login){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.login(),
                            login.senha()
                    )
            );
            Proprietario proprietario = (Proprietario) authentication.getPrincipal();
            return tokenService.createToken(proprietario);
        }catch (BadCredentialsException e){
            throw new UnauthorizedException("Login ou senha inválidos.");
        }

    }
}
