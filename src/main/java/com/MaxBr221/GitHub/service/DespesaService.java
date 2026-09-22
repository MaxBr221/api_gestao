package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.DespesaRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.DespesaResponseDTO;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Despesa;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.repository.DespesaRepository;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DespesaService {
    private final DespesaRepository despesaRepository;
    private final ProprietarioRepository proprietarioRepository;

    public void criarDespesa(DespesaRequestDTO despesaDTO){
        Long tenantId = TenantContext.getTenantId();
        Proprietario proprietario = proprietarioRepository.findById(tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Proprietario não encontrado!"));

        Despesa despesa = new Despesa();
        despesa.setDescricao(despesaDTO.descricao());
        despesa.setProprietario(proprietario);
        despesa.setObservacao(despesaDTO.observacao());
        despesa.setValor(despesaDTO.valor());
        despesa.setData(despesaDTO.data());
        despesa.setCategoria(despesaDTO.categoria());
        despesaRepository.save(despesa);
    }

    public DespesaResponseDTO findById(Long id){
        Long tenantId = TenantContext.getTenantId();
        Despesa despesa = despesaRepository.findByIdDespesaAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Proprietario não existente!"));
        return new DespesaResponseDTO(despesa);
    }
    public List<DespesaResponseDTO> findAll(){
        Long tenantId = TenantContext.getTenantId();
        return despesaRepository.findAllByDespensaProprietarioId(tenantId)
                .stream()
                .map(despesa -> new DespesaResponseDTO(despesa))
                .toList();
    }
    public void delete(Long id){
        Long tenantId = TenantContext.getTenantId();
        Despesa despesa = despesaRepository.findByIdDespesaAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Proprietario não existente!"));
        despesaRepository.delete(despesa);
    }
    public DespesaResponseDTO update(Long id, DespesaRequestDTO despesaDTO){
        Long tenantId = TenantContext.getTenantId();
        Despesa despesa = despesaRepository.findByIdDespesaAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Proprietario não existente!"));
        despesa.setCategoria(despesaDTO.categoria());
        despesa.setDescricao(despesaDTO.descricao());
        despesa.setObservacao(despesaDTO.observacao());
        despesa.setValor(despesaDTO.valor());
        despesa.setData(despesaDTO.data());
        Despesa despesaSalva = despesaRepository.save(despesa);

        return new DespesaResponseDTO(despesaSalva);

    }


}
