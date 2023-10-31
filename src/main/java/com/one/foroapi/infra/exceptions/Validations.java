package com.one.foroapi.infra.exceptions;

public class Validations extends RuntimeException {
    public Validations(String message) {
        super(message);
    }
}
