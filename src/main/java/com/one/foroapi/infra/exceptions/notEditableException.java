package com.one.foroapi.infra.exceptions;

public class notEditableException extends RuntimeException {
    public notEditableException(String message) {
        super(message);
    }
}
