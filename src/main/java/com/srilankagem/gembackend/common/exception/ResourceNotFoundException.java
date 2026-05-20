package com.srilankagem.gembackend.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource , String field) {
        super(String.format("Resource not found - %s with field - %s" , resource , field));
    }
}
