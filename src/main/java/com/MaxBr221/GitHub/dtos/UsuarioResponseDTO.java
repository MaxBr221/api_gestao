package com.MaxBr221.GitHub.dtos;

import com.MaxBr221.GitHub.model.Role;
import com.MaxBr221.GitHub.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String login,
        String telefone) {
    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getTelefone());
    }
}