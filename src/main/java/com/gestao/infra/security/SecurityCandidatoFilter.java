package com.gestao.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.io.IOException;


@Component
public class SecurityCandidatoFilter extends OncePerRequestFilter {

    @Autowired
    TokenServiceCandidato tokenServiceCandidato;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

     //   SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");
// verificando se a requisição começa com candidato
        if (request.getRequestURI().startsWith("/candidato")) {


            // verificação


            if (header != null) {
                var token = this.tokenServiceCandidato.validateToken(header);

                //verificando se o token tem autorizaçao
                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    return;

                }
// recuperando o id do candidato
                request.setAttribute("candidato_id", token.getSubject());
                var roles = token.getClaim("roles").asList(Object.class);
             var grants = roles.stream()
                     .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                     .toList();
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants );
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }

        //receber o token
        filterChain.doFilter(request, response);
    }
}
