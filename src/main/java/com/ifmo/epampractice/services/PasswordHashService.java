package com.ifmo.epampractice.services;

import com.ifmo.epampractice.security.SecureString;

public interface PasswordHashService {

    String getHash(SecureString password);

    boolean isMatching(String hash, SecureString password);
}
