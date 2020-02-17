package com.ifmo.epampractice.services;

import com.ifmo.epampractice.exceptions.HashingException;

public interface PasswordHashService {
    String getHash(String password) throws HashingException;
    boolean isMatching(String hash, String password) throws HashingException;
}
