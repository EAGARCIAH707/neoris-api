package com.neoris.api.util.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String ex) {
        super(ex);
    }
}
