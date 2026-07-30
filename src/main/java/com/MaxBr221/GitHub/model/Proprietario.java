package com.MaxBr221.GitHub.model;

import com.MaxBr221.GitHub.dtos.authDTO.Cadastro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proprietario implements UserDetails {
    //admin
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String senha;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Proprietario(Cadastro cadastro){
        BeanUtils.copyProperties(cadastro, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public Proprietario(String nome, String login, String senha, String telefone, Role role) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.role = role;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }
}
