package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.ProprietarioRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.ProprietarioResponseDTO;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProprietarioService {
    private final ProprietarioRepository proprietarioRepository;
    private final PasswordEncoder passwordEncoder;

    public ProprietarioResponseDTO update(ProprietarioRequestDTO userDTO){
        Long tenantId = TenantContext.getTenantId();
        Proprietario usuarioBuscado = proprietarioRepository.findById(tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado!"));
        usuarioBuscado.setNome(userDTO.nome());
        usuarioBuscado.setTelefone(userDTO.telefone());
        //add set para mudar senha e login
        Proprietario userSalvo = proprietarioRepository.save(usuarioBuscado);
        return new ProprietarioResponseDTO(userSalvo);
    }
    //editar senha
    public void mudarSenha(String login, String novaSenha){
        Proprietario proprietario = proprietarioRepository.findByLogin(login)
                .orElseThrow(()-> new ResourceNotFoundException("Login não encontrado!"));

        String senhaCriptografada = passwordEncoder.encode(novaSenha);
        proprietario.setSenha(senhaCriptografada);
        proprietarioRepository.save(proprietario);

    }

}
