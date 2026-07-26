package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.BarbeiroRequestDTO;
import com.MaxBr221.GitHub.dtos.BarbeiroResponseDTO;
import com.MaxBr221.GitHub.dtos.ProprietarioRequestDTO;
import com.MaxBr221.GitHub.dtos.ProprietarioResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Barbeiro;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.repository.BarbeiroRepository;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProprietarioService {
    private final ProprietarioRepository proprietarioRepository;
    private final BarbeiroRepository barbeiroRepository;

    public ProprietarioResponseDTO create(ProprietarioRequestDTO userDTO){
        if(proprietarioRepository.existsByLogin(userDTO.login())){
            throw new EventFullException("Usuário com login já existente!");
        }
        Proprietario usuario = new Proprietario();
        BeanUtils.copyProperties(userDTO, userDTO);
        Proprietario salvo = proprietarioRepository.save(usuario);
        return new ProprietarioResponseDTO(salvo);
    }
    public ProprietarioResponseDTO findById(Long id){
        Proprietario usuario = proprietarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado!"));
        return new ProprietarioResponseDTO(usuario);
    }
    public List<ProprietarioResponseDTO> findAll(){
        return proprietarioRepository.findAll()
                .stream()
                .map(usuario -> new ProprietarioResponseDTO(usuario))
                .toList();
    }
    public void delete(Long id){
        Proprietario usuario = proprietarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado!"));

        proprietarioRepository.delete(usuario);
    }
    public ProprietarioResponseDTO update(Long id, ProprietarioRequestDTO userDTO){
        Proprietario usuarioBuscado = proprietarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Usuário não encontrado!"));
        usuarioBuscado.setNome(userDTO.nome());
        usuarioBuscado.setTelefone(userDTO.telefone());
        //add set para mudar senha e login
        Proprietario userSalvo = proprietarioRepository.save(usuarioBuscado);
        return new ProprietarioResponseDTO(userSalvo);
    }

}
