package com.gestao.domain.candidato.entity;


import com.gestao.domain.candidato.Candidato;
import com.gestao.domain.vagas.Vaga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Candidato_Vagas")
public class CandidatoVagaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;




    @ManyToOne
    @JoinColumn(name = "candidato_id",insertable = false,updatable = false)
    private Candidato candidato;


    @ManyToOne
    @JoinColumn(name = "vaga_id",insertable = false,updatable = false)

    private Vaga vaga;

    @Column(name ="candidato_id", nullable = false)
    private  Long candidatoId;


    @Column(name ="vaga_id", nullable = false)
    private  Long vagaId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
