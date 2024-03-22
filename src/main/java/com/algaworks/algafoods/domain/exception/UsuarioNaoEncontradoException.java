package com.algaworks.algafoods.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{


    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }

    public UsuarioNaoEncontradoException(Long cozinhaId) {
        this(String.format("Não existe um cadastro de usuário com código %d", cozinhaId));
    }

}
