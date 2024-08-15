package com.gestao.domain.empresa.service;


import com.gestao.domain.empresa.Empresa;
import com.gestao.domain.empresa.EmpresaRepository;
import com.gestao.infra.exceptions.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Empresa execute(Empresa empresa){
        // verificando se o usuario existe
        this.empresaRepository.findByNameOrEmail(empresa.getName(),empresa.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });

        var password=passwordEncoder.encode(empresa.getPassword());
empresa.setPassword(password);


        return this.empresaRepository.save(empresa);

    }


}
