package com.gestao.domain.empresa;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {


//    Optional para validação
    Optional<Empresa> findByNameOrEmail (String name, String email);
    Optional<Empresa> findByName(String name);

    Optional<Empresa> findById (Long id);



}
