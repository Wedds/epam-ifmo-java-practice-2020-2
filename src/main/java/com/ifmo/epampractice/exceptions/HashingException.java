package com.ifmo.epampractice.exceptions;

public class HashingException extends Exception {
    public HashingException(String errorMessage, Throwable innerException) {
        super(errorMessage, innerException);
    }
}
