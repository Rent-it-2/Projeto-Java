package com.example.rent.it.exceptions;

import javax.naming.AuthenticationException;

public class UsuarioNaoEncontradoException extends AuthenticationException {

    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }

    public UsuarioNaoEncontradoException(String msg, Throwable t) {
        super();
    }
}