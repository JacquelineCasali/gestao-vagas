package com.gestao.infra.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Usuário não encontrado");

    }
}
