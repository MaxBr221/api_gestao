package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Atendimento;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    //só admin (proprietario) manipula tudo
    @Transactional
    public AtendimentoResponseDTO create(AtendimentoRequestDTO atendimentoRequestDTO){
        if(atendimentoRepository.existsByDataServico(atendimentoRequestDTO.data())){
            throw new EventFullException("Data de atendimento já ocupada!");
        }
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

        if(atendimentoRepository.existsByDataServico(atendimentoRequestDTO.data())){
            throw new EventFullException("Data de atendimento já ocupada!");
        }
        atendimento.setObservacao(atendimentoRequestDTO.observacao());
        atendimento.setFormaPagamento(atendimentoRequestDTO.formaPagamento());
        atendimento.setDataServico(atendimentoRequestDTO.data());
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }
    // funcionalidade de listar atendimentos por um dia especifico
    public List<AtendimentoResponseDTO> listarAtendimentos(LocalDate dataAtendimento){

        LocalDateTime incio = dataAtendimento.atStartOfDay();
        LocalDateTime fim = dataAtendimento.atTime(LocalTime.MAX);
        List<Atendimento> atendimentosList = atendimentoRepository.findByDataServicoBetween(incio, fim);

        if(atendimentosList.isEmpty()){
            throw new ResourceNotFoundException("Atendimentos não existente nessa data!");
        }
        return atendimentosList
                .stream()
                .map(AtendimentoResponseDTO::new)
                .toList();
    }
}
