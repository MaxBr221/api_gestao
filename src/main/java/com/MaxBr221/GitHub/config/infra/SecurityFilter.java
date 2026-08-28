package com.MaxBr221.GitHub.config.infra;


import com.MaxBr221.GitHub.model.Proprietario;
import com.MaxBr221.GitHub.repository.ProprietarioRepository;
import com.MaxBr221.GitHub.tenant.TenantContext;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final ProprietarioRepository proprietarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       try{
           if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
               response.setStatus(HttpServletResponse.SC_OK);
               filterChain.doFilter(request, response);
               return;
           }
           String path = request.getRequestURI();

           if (path.startsWith("/auth")) {
               filterChain.doFilter(request, response);
               return;
           }

           String token = recoverToken(request);

           if (token != null) {
               String login = tokenService.validateToken(token);
               if (login != null) {
                   DecodedJWT decodedJWT = JWT.decode(token);

                   Long tenantId = decodedJWT.getClaim("tenantId")
                           .asLong();

                   TenantContext.setTenantId(tenantId);
                   Optional<Proprietario> proprietario = proprietarioRepository.findByLogin(login);

                   if (proprietario.isPresent()) {
                       var auth = new UsernamePasswordAuthenticationToken(
                               proprietario, null, proprietario.get().getAuthorities());
                       SecurityContextHolder.getContext().setAuthentication(auth);
                   }
               }
               filterChain.doFilter(request, response);

           }
       }finally {
           TenantContext.clear();

       }

    }
    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return null;
        }
        return authHeader.substring(     7);
    }

}
