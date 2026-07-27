package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.ServicoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.ServicoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Servico;
import com.MaxBr221.GitHub.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {
    public final ServicoRepository servicoRepository;

    public ServicoResponseDTO create(ServicoRequestDTO servicoRequestDTO){
        Servico servico = servicoRepository.findByNome(servicoRequestDTO.nome())
                .orElseThrow(()-> new EventFullException("Serviço já cadastrado!"));

        Servico novoServico = new Servico();
        BeanUtils.copyProperties(servicoRequestDTO, novoServico);
        Servico servicoSalvo = servicoRepository.save(novoServico);

        return new ServicoResponseDTO(servicoSalvo);
    }
    public void delete(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Serviço não existente!"));
        servicoRepository.delete(servico);
    }
    public ServicoResponseDTO findById(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Serviço não existente!"));

        return new ServicoResponseDTO(servico);
    }
    public List<ServicoResponseDTO> findAll(){
        return servicoRepository.findAll()
                .stream()
                .map(servico -> new ServicoResponseDTO(servico))
                .toList();
    }
    public ServicoResponseDTO update(Long id, ServicoRequestDTO servicoRequestDTO){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Serviço não existente!"));

        servico.setDescricao(servicoRequestDTO.descricao());
        servico.setNome(servicoRequestDTO.nome());
        servico.setPreco(servicoRequestDTO.preco());
        BeanUtils.copyProperties(servicoRequestDTO, servico);
        Servico servicoSalvo = servicoRepository.save(servico);
        return new ServicoResponseDTO(servicoSalvo);
    }
}
