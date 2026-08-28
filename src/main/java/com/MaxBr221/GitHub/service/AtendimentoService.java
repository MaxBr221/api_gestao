package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoResponseDTO;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Atendimento;
import com.MaxBr221.GitHub.model.AtendimentoServico;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.model.Servico;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.repository.ServicoRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;
    private final ProprietarioRepository proprietarioRepository;
    private final ServicoRepository servicoRepository;

    @Transactional
    public AtendimentoResponseDTO create(AtendimentoRequestDTO dto) {
        Long tenantId = TenantContext.getTenantId();
        Atendimento atendimento = new Atendimento();
        LocalDateTime agora = LocalDateTime.now();
        atendimento.setDataServico(agora);
        atendimento.setFormaPagamento(dto.formaPagamento());
        atendimento.setObservacao(dto.observacao());
        Proprietario proprietario = proprietarioRepository
                .findById(tenantId)
                .orElseThrow(() ->
                        new RuntimeException("Proprietário não encontrado"));
        atendimento.setProprietario(proprietario);

        List<Servico> servicos =
                servicoRepository.findAllByIdInAndProprietarioId(
                        dto.servicosIds(),
                        tenantId
                );

        BigDecimal valorTotal = servicos.stream()
                .map(Servico::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        atendimento.setValor(valorTotal);

        List<AtendimentoServico> atendimentoServicos =
                servicos.stream()
                        .map(servico -> {

                            AtendimentoServico atendimentoServico =
                                    new AtendimentoServico();

                            atendimentoServico.setAtendimento(atendimento);
                            atendimentoServico.setServico(servico);
                            atendimentoServico.setTotal(servico.getPreco());

                            return atendimentoServico;
                        })
                        .toList();
        atendimento.setAtendimentos(atendimentoServicos);

        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }
    public void delete(Long id){
        Long tenantId = TenantContext.getTenantId();
        Atendimento atendimento = atendimentoRepository.findByIdAtendimentoAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));
        atendimentoRepository.delete(atendimento);
    }
    public AtendimentoResponseDTO findById(Long id){
        Long tenantId = TenantContext.getTenantId();
        Atendimento atendimento = atendimentoRepository.findByIdAtendimentoAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));
        return new AtendimentoResponseDTO(atendimento);
    }
    public List<AtendimentoResponseDTO> findAll(){
        Long tenantId = TenantContext.getTenantId();
        return atendimentoRepository.findAllByProprietarioId(tenantId)
                .stream()
                .map(atendimento -> new AtendimentoResponseDTO(atendimento))
                .toList();
    }
    public AtendimentoResponseDTO update(Long id, AtendimentoRequestDTO atendimentoRequestDTO){
        Long tenantId = TenantContext.getTenantId();
        Atendimento atendimento = atendimentoRepository.findByIdAtendimentoAndProprietarioId(id, tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Atendimento não encotrado!"));

        atendimento.setObservacao(atendimentoRequestDTO.observacao());
        atendimento.setFormaPagamento(atendimentoRequestDTO.formaPagamento());
        LocalDateTime agora = LocalDateTime.now();
        atendimento.setDataServico(agora);
        Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }
    // funcionalidade de listar atendimentos por um dia especifico
    public List<AtendimentoResponseDTO> listarAtendimentos(LocalDate dataAtendimento){
        Long tenantId = TenantContext.getTenantId();
        LocalDateTime incio = dataAtendimento.atStartOfDay();
        LocalDateTime fim = dataAtendimento.atTime(LocalTime.MAX);
        List<Atendimento> atendimentosList = atendimentoRepository.findByProprietarioIdAndDataServicoBetween(tenantId ,incio, fim);

        if(atendimentosList.isEmpty()){
            throw new ResourceNotFoundException("Atendimentos não existente nessa data!");
        }
        return atendimentosList
                .stream()
                .map(AtendimentoResponseDTO::new)
                .toList();
    }
}
