package com.gestao.domain.candidato.service;

import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.candidato.repository.CandidatoRepository;
import com.gestao.domain.candidato.repository.CandidatoVagaRepository;
import com.gestao.domain.candidato.entity.CandidatoVagaEntity;
import com.gestao.domain.vagas.Vaga;
import com.gestao.domain.vagas.VagaRepository;
import com.gestao.infra.exceptions.UserNotFoundException;
import com.gestao.infra.exceptions.VagaNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncricaoCandidatoVagaServiceTest {


    // injeção de depedencia mokado

@InjectMocks
private  IncricaoCandidatoVagaService incricaoCandidatoVagaService;

  @Mock
    private CandidatoRepository candidatoRepository;

    @Mock
    private VagaRepository vagaRepository;

    @Mock
    private CandidatoVagaRepository candidatoVagaRepository;

@Test
@DisplayName("Nao deve ser possivel se candidatar a vaga se o candidato não for  encontrado")
    public void nao_aplica_vaga_com_candidato_nao_encontrado(){

    try {
        incricaoCandidatoVagaService.execute(null,null);
    }catch (Exception e){
        assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
}

    @Test
    @DisplayName("Nao deve ser possivel se candidatar a vaga se a vaga não for  encontrado")
    public void nao_aplica_vaga_com_vaga_nao_encontrado() {
        Long id = 1L;
        var candidato= new Candidato();
        candidato.setId(id);
//        var idVaga=UUID.randomUUID();

when(candidatoRepository.findById(id)).thenReturn(Optional.of(candidato));

        try {
            incricaoCandidatoVagaService.execute(id, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(VagaNotFoundException.class);
        }

    }
@Test
    public void aplicar_a_nova_vaga(){
    Long id = 1L;
    var candidato= new Candidato();
    candidato.setId(id);

    var vaga= new Vaga();
    vaga.setId(id);
       var aplicarVaga = CandidatoVagaEntity.builder().candidatoId(id)
            .vagaId(id).build();


   var aplicarVagaCreated=CandidatoVagaEntity.builder().id(Long.parseLong(id.toString()))
          .build();



when(candidatoRepository.findById(id)).thenReturn(Optional.of(new Candidato()));

    when(vagaRepository.findById(id)).thenReturn(Optional.of(new Vaga()));


    when(candidatoVagaRepository.save(aplicarVaga)).thenReturn(aplicarVagaCreated);


   var result= incricaoCandidatoVagaService.execute(id,id);
    assertThat(result).hasFieldOrProperty("id");
    assertNotNull(result.getId());
}

}
