package com.gestao.candidato;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Table(name = "candidato")
@Entity(name = "candidato")
@Data
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


  @NotBlank(message = "Username não pode ser vazio")
    private String username;
@Email(message = "O campo deve conter um email valido")
   private String email ;


  @Length(min=4 , message = "A senha deve conter mais de 4 caracteres")
    private String password;
  private String  description;
    private  String curriculum;
}
