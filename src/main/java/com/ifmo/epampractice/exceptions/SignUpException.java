package com.ifmo.epampractice.exceptions;

public class SignUpException extends RuntimeException {
    public SignUpException(String errorMessage) {
        super(errorMessage);
    }
}
