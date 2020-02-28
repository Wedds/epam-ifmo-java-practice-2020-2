package com.ifmo.epampractice.services;

import com.ifmo.epampractice.security.SecureString;

public interface LogInService {
    boolean getAuth(String login, SecureString password);
}
