package com.seguridad.seguridad_calidad_back.core.exceptions;

public class EmailAlreadyRegisteredException extends Exception {
    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
