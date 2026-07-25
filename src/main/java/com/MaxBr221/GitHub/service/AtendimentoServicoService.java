package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.AtendimentoServicoRequestDTO;
import com.MaxBr221.GitHub.dtos.AtendimentoServicoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.model.AtendimentoServico;
import com.MaxBr221.GitHub.repository.AtendimentoServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoServicoService {
    private final AtendimentoServicoRepository atendimentoServicoRepository;

    public AtendimentoServicoResponseDTO atendimentoResponseDTO(AtendimentoServicoRequestDTO atendimento){
        if(atendimentoServicoRepository.existsById(atendimento.atendimentoId())){
            throw new EventFullException("Atendimento já criado!");
        }

        AtendimentoServico novoAtendimento = new AtendimentoServico();
        BeanUtils.copyProperties(atendimento, novoAtendimento);
        AtendimentoServico atendimentoSalvo = atendimentoServicoRepository.save(novoAtendimento);
        return new AtendimentoServicoResponseDTO(atendimentoSalvo);

    }
    public AtendimentoServicoResponseDTO findById(Long id){
        AtendimentoServico atendimentoServico = atendimentoServicoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Atendimento não encotrado!"));
        return new AtendimentoServicoResponseDTO(atendimentoServico);
    }
    public List<AtendimentoServicoResponseDTO> findAll(){
        return atendimentoServicoRepository.findAll()
                .stream()
                .map(atendimentoServico -> new AtendimentoServicoResponseDTO(atendimentoServico))
                .toList();
    }
    public void delete(Long id){
        AtendimentoServico atendimentoServico = atendimentoServicoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Atendimento não encotrado!"));
        atendimentoServicoRepository.delete(atendimentoServico);
    }

}
