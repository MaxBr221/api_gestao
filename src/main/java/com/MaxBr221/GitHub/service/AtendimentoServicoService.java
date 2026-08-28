package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoServicoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoServicoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.AtendimentoServico;
import com.MaxBr221.GitHub.repository.AtendimentoServicoRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoServicoService {
    private final AtendimentoServicoRepository atendimentoServicoRepository;

    public AtendimentoServicoResponseDTO create(AtendimentoServicoRequestDTO atendimento){
        Long tenantId = TenantContext.getTenantId();
        if(atendimentoServicoRepository.existsByIdAndAtendimentoProprietarioId(atendimento.atendimentoId(),tenantId)){
            throw new EventFullException("Atendimento já criado!");
        }
        AtendimentoServico novoAtendimento = new AtendimentoServico();
        BeanUtils.copyProperties(atendimento, novoAtendimento);
        AtendimentoServico atendimentoSalvo = atendimentoServicoRepository.save(novoAtendimento);
        return new AtendimentoServicoResponseDTO(atendimentoSalvo);

    }
    public AtendimentoServicoResponseDTO findById(Long id){
        Long tenantId = TenantContext.getTenantId();
        AtendimentoServico atendimentoServico = atendimentoServicoRepository.findByIdAndAtendimentoProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));
        return new AtendimentoServicoResponseDTO(atendimentoServico);
    }
    public List<AtendimentoServicoResponseDTO> findAll(){
        Long tenantId = TenantContext.getTenantId();
        return atendimentoServicoRepository.findAllByAtendimentoProprietarioId(tenantId)
                .stream()
                .map(atendimentoServico -> new AtendimentoServicoResponseDTO(atendimentoServico))
                .toList();
    }
    public void delete(Long id){
        Long tenantId = TenantContext.getTenantId();
        AtendimentoServico atendimentoServico = atendimentoServicoRepository.findByIdAndAtendimentoProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));
        atendimentoServicoRepository.delete(atendimentoServico);
    }

}
