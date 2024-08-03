package com.gestao.infra.exceptions;

public class UserFoundException extends RuntimeException {

public UserFoundException(){
    super("Nome ou email Já existe");
}

}
