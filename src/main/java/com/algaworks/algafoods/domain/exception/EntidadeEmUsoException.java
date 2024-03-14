package com.algaworks.algafoods.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntidadeEmUsoException extends Throwable {
    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
