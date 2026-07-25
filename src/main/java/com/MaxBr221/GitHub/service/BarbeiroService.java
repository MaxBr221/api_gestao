package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.BarbeiroRequestDTO;
import com.MaxBr221.GitHub.dtos.BarbeiroResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.model.Barbeiro;
import com.MaxBr221.GitHub.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {
    private final BarbeiroRepository barbeiroRepository;

    //só o Proprietário (ADMIN) pode manipular essa classe
    public BarbeiroResponseDTO createBarbeiro(BarbeiroRequestDTO barbeiroRequestDTO){
        if(barbeiroRepository.existsByTelefone(barbeiroRequestDTO.telefone())){
            throw new EventFullException("Barbeiro já existente!");
        }

        Barbeiro novoBarbeiro = new Barbeiro();
        BeanUtils.copyProperties(barbeiroRequestDTO, novoBarbeiro);
        Barbeiro barbeiroSalvo = barbeiroRepository.save(novoBarbeiro);
        return new BarbeiroResponseDTO(barbeiroSalvo);
    }
    public void delete(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));
        barbeiroRepository.delete(barbeiro);
    }
    public BarbeiroResponseDTO findById(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));
        return new BarbeiroResponseDTO(barbeiro);
    }
    public List<BarbeiroResponseDTO> findAll(){
        return barbeiroRepository.findAll().stream()
                .map(barbeiro -> new BarbeiroResponseDTO(barbeiro))
                .toList();

    }
    public BarbeiroResponseDTO update(Long id, BarbeiroRequestDTO barbeiroDTO){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));
        barbeiro.setNome(barbeiroDTO.nome());
        barbeiro.setTelefone(barbeiroDTO.telefone());
        Barbeiro barbeiroSalvo = barbeiroRepository.save(barbeiro);
        return new BarbeiroResponseDTO(barbeiroSalvo);
    }
}
