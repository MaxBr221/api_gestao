package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.Proprietario;

public record ProprietarioResponseDTO(
        Long id,
        String nome,
        String login,
        String telefone) {
    public ProprietarioResponseDTO(Proprietario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getTelefone());
    }
}