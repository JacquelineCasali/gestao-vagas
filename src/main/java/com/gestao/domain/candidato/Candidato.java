package com.gestao.domain.candidato;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


@Data
@Entity(name="candidatos")

public class Candidato {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    @NotBlank(message = "Name não pode ser vazio")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Email não pode ser vazio")
    @Column(unique = true)
@Email(message = "O campo deve conter um email valido")
   private String email ;


@Length(min=4 , message = "A senha deve conter mais de 4 caracteres")
    private String password;
  private String  description;
    private  String curriculum;

        @CreationTimestamp
    private LocalDateTime createdAt;
}
