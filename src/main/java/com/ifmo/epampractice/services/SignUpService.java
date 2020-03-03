package com.ifmo.epampractice.services;

import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.UserRole;
import com.ifmo.epampractice.exceptions.SignUpException;
import com.ifmo.epampractice.security.SecureString;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SignUpService {
    boolean userExists(String email);

    UsersEntity signUp(String email, SecureString password, String name,
                       LocalDate birthDate, String phone, String address,
                       UserRole role, BigDecimal reputation);
}
