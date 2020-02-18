package com.ifmo.epampractice.services;

import com.ifmo.epampractice.exceptions.HashingException;
import com.ifmo.epampractice.security.SecureString;

public interface PasswordHashService {
    String getHash(SecureString password) throws HashingException, IllegalArgumentException;
    boolean isMatching(String hash, SecureString password) throws HashingException, IllegalArgumentException;
}
