package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoRequestDTO;
import com.MaxBr221.GitHub.dtos.entitysDTO.AtendimentoResponseDTO;
import com.MaxBr221.GitHub.exception.EventFullException;
import com.MaxBr221.GitHub.exception.ResourceNotFoundException;
import com.MaxBr221.GitHub.model.Atendimento;
import com.MaxBr221.GitHub.model.AtendimentoServico;
import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.model.Servico;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    //só admin (proprietario) manipula tudo
    @Transactional
    public AtendimentoResponseDTO create(AtendimentoRequestDTO dto) {
        if (atendimentoRepository.existsByDataServico(dto.data())) {
            throw new EventFullException("Data de atendimento já ocupada!");
        }
        Atendimento atendimento = new Atendimento();
        atendimento.setDataServico(dto.data());
        atendimento.setFormaPagamento(dto.formaPagamento());
        atendimento.setObservacao(dto.observacao());
        Proprietario proprietario = proprietarioRepository
                .findById(dto.usuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Proprietário não encontrado"));
        atendimento.setProprietario(proprietario);

        List<Servico> servicos =
                servicoRepository.findAllById(dto.servicosIds());

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
