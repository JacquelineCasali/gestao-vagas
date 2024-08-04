package com.gestao.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
//validando rotas
@EnableMethodSecurity
public class SecurityConfigurations {


    @Autowired
    SecurityEmpresaFilter securityFilter;

    @Autowired
    SecurityCandidatoFilter securityCandidatoFilter;

    //configurações
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //desabilitar o security
        http.csrf(csrf -> csrf.disable())
                // rotas sem login
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/candidato").permitAll()
                            .requestMatchers("/empresa").permitAll()
                            .requestMatchers("/empresa/login").permitAll()
                         .requestMatchers("/candidato/login").permitAll()
                      .requestMatchers("/swagger-ui/index.html","/v3/api-docs/**",
                                    "/swagger-ui/**","/swagger-resource/**"
                                    ).permitAll()
                    ;
                    //demais rotas precisa de autenticação
                    auth.anyRequest().authenticated();
                })


               .addFilterBefore(securityCandidatoFilter,BasicAuthenticationFilter.class)

                    .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);



        return http.build();
    }
    // nao deixar senha aparente
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
