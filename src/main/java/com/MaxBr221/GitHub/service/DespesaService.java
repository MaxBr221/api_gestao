package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.DespesaRequestDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Despesa;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.repository.DespesaRepository;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
