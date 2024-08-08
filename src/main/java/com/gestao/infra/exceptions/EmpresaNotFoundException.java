package com.gestao.infra.exceptions;

public class EmpresaNotFoundException extends RuntimeException {


    public EmpresaNotFoundException(){
        super("Empresa não encontrado");

    }
}
