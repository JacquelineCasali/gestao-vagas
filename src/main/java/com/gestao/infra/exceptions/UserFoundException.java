package com.gestao.infra.exceptions;

public class UserFoundException extends RuntimeException {

public UserFoundException(){
    super("Usuário ou email Já existe");
}

}
