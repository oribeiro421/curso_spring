package com.algaworks.algafoods.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
