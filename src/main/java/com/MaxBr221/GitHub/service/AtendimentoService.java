package com.MaxBr221.GitHub.service;

import com.MaxBr221.GitHub.dtos.AtendimentoRequestDTO;
import com.MaxBr221.GitHub.dtos.AtendimentoResponseDTO;
import com.MaxBr221.GitHub.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoResponseDTO create(AtendimentoRequestDTO atendimentoRequestDTO){

    }

}
