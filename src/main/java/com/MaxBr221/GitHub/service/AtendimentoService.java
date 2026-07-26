package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.AtendimentoRequestDTO;
import com.MaxBr221.GitHub.dtos.AtendimentoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Atendimento;
import com.MaxBr221.GitHub.model.Barbeiro;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import com.MaxBr221.GitHub.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;
    private final BarbeiroRepository barbeiroRepository;

    //só admin (proprietario) manipula tudo
    @Transactional
    public AtendimentoResponseDTO create(AtendimentoRequestDTO atendimentoRequestDTO){
        if(atendimentoRepository.existsByData(atendimentoRequestDTO.data())){
            throw new EventFullException("Data de atendimento já ocupada!");
        }
        Barbeiro barbeiro = barbeiroRepository.findById(atendimentoRequestDTO.barbeiroId())
                .orElseThrow(()-> new ResourceNotFoundException("Barbeiro não existente!"));
        Atendimento atendimento = new Atendimento();
        BeanUtils.copyProperties(atendimentoRequestDTO, atendimento);
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }
    public void delete(Long id){
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));
        atendimentoRepository.delete(atendimento);
    }
    public AtendimentoResponseDTO findById(Long id){
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));
        return new AtendimentoResponseDTO(atendimento);
    }
    public List<AtendimentoResponseDTO> findAll(){
        return atendimentoRepository.findAll()
                .stream()
                .map(atendimento -> new AtendimentoResponseDTO(atendimento))
                .toList();
    }
    public AtendimentoResponseDTO update(Long id, AtendimentoRequestDTO atendimentoRequestDTO){
        Atendimento atendimento = atendimentoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));

        if(atendimentoRepository.existsByData(atendimentoRequestDTO.data())){
            throw new EventFullException("Data de atendimento já ocupada!");
        }
        atendimento.setObservacao(atendimentoRequestDTO.observacao());
        atendimento.setFormaPagamento(atendimentoRequestDTO.formaPagamento());
        atendimento.setDataServico(atendimentoRequestDTO.data());
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }

}
