package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {
    private final ProprietarioRepository proprietarioRepository;

    //Responsável por carregar o usuario para o spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return proprietarioRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("Proprietario não existente!"));
    }
}
