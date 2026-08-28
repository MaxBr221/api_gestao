package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.ServicoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.ServicoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.model.Servico;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.repository.ServicoRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final ProprietarioRepository proprietarioRepository;

    public ServicoResponseDTO create(ServicoRequestDTO servicoRequestDTO){
        Long tenantId = TenantContext.getTenantId();
        if(servicoRepository.existsByNomeAndProprietarioId(servicoRequestDTO.nome(), tenantId)){
            throw new EventFullException("Serviço já cadastrado!");
        }
        Proprietario proprietario = proprietarioRepository.findById(tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Proprietario não encontrado!"));

        Servico novoServico = new Servico();
        BeanUtils.copyProperties(servicoRequestDTO, novoServico);
        novoServico.setProprietario(proprietario);
        Servico servicoSalvo = servicoRepository.save(novoServico);

        return new ServicoResponseDTO(servicoSalvo);
    }
    public void delete(Long id){
        Long tenantId = TenantContext.getTenantId();
        Servico servico = servicoRepository.findByIdAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Serviço não existente!"));
        servicoRepository.delete(servico);
    }
    public ServicoResponseDTO findById(Long id){
        Long tenantId = TenantContext.getTenantId();
        Servico servico = servicoRepository.findByIdAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Serviço não existente!"));
        return new ServicoResponseDTO(servico);
    }
    public List<ServicoResponseDTO> findAll(){
        Long tenantId = TenantContext.getTenantId();
        return servicoRepository.findAllByProprietarioId(tenantId)
                .stream()
                .map(servico -> new ServicoResponseDTO(servico))
                .toList();
    }
    public ServicoResponseDTO update(Long id, ServicoRequestDTO servicoRequestDTO){
        Long tenantId = TenantContext.getTenantId();
        Servico servico = servicoRepository.findByIdAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Serviço não existente!"));

        servico.setDescricao(servicoRequestDTO.descricao());
        servico.setNome(servicoRequestDTO.nome());
        servico.setPreco(servicoRequestDTO.preco());
        BeanUtils.copyProperties(servicoRequestDTO, servico);
        Servico servicoSalvo = servicoRepository.save(servico);
        return new ServicoResponseDTO(servicoSalvo);
    }
    
}
