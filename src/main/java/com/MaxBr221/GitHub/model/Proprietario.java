package com.MaxBr221.GitHub.model;

import com.MaxBr221.GitHub.dtos.auth.Cadastro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proprietario {
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

}
