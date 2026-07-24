package com.MaxBr221.GitHub.config.infra;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(path.startsWith("/auth")){
            filterChain.doFilter(request, response);
        }

        String token = recoverToken(request);

//        if(token != null){
//            String login = tokenService.validateToken(token);
//            if (login != null){
//                Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
//                if(usuario.isPresent()){
//                    var auth = new UsernamePasswordAuthenticationToken(
//                            usuario,null,usuario.get().getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//            }


        filterChain.doFilter(request, response);

    }
    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        return authHeader.substring(     7);
    }
}
