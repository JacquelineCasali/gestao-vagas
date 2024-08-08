package com.gestao.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class SecurityEmpresaFilter extends OncePerRequestFilter {

    @Autowired
    TokenServiceEmpresa tokenServiceEmpresa;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       // SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");
        // verificando se a requisição começa com candidato
      if (request.getRequestURI().startsWith("/empresa")) {

           // verificação
           if (header != null) {
               var token = this.tokenServiceEmpresa.validateToken(header);
               if (token == null) {
                   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                   return;

               }

// recuperando o empresaId
               request.setAttribute("empresa_id", token.getSubject());
               var roles = token.getClaim("roles").asList(Object.class);
               var grants = roles.stream()
                       .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
                       .toList();

               // inserindo o usuario da empresa
//   lista vazia Collections.emptyList()
               UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
               SecurityContextHolder.getContext().setAuthentication(auth);
               }

       }
        //receber o token
        filterChain.doFilter(request, response);
    }
}
