package com.gestao.infra.exceptions;

public class VagaNotFoundException extends RuntimeException{

    public VagaNotFoundException(){
        super("Vaga não encontrado");

    }
}
