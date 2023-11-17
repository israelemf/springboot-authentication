package com.auth.exceptions;

public class GlobalException extends RuntimeException{
    public GlobalException(String mensagem) {
        super(mensagem);
    }

    public GlobalException(String mensagem, Object ... params) {
        super(String.format(mensagem, params));
    }
}
