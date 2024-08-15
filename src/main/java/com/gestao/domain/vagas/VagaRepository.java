package com.gestao.domain.vagas;



import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

// contenha a palavra LIKE (SELECT)

    // SELECT * from empresa where description like %filter%

    //IgnoreCase - pesquisa por maiscula ou minuscula
    List<Vaga> findByDescriptionContainingIgnoreCase(String filter);

   List<Vaga> findByEmpresaId(Long id);

}
