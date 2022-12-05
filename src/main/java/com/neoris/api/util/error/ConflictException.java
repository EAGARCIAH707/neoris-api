package com.neoris.api.util.error;

public class ConflictException extends RuntimeException {
    public ConflictException(String ex) {
        super(ex);
    }
}
