package com.ciandt.people.bootcamp.cleanarch.domain.exception;

public class LutadorNotFoundException extends RuntimeException {

    public LutadorNotFoundException(Long id) {
        super("Não foi possível achar o lutador com id: " + id);
    }
}
