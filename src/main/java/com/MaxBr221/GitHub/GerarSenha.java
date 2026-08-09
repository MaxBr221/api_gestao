package com.MaxBr221.GitHub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senha = "11";

        String hash = encoder.encode(senha);

        System.out.println(hash);
    }
}