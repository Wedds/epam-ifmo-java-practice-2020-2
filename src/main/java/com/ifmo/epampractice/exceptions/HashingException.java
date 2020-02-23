package com.ifmo.epampractice.exceptions;

public class HashingException extends RuntimeException {
    public HashingException(String errorMessage, Throwable innerException) {
        super(errorMessage, innerException);
    }
}
