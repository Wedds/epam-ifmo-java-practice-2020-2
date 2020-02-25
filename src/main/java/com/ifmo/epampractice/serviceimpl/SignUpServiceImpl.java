package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.UserRole;
import com.ifmo.epampractice.exceptions.SignUpException;
import com.ifmo.epampractice.security.SecureString;
import com.ifmo.epampractice.services.PasswordHashService;
import com.ifmo.epampractice.services.SignUpService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SignUpServiceImpl implements SignUpService {

    private UsersDao usersDao;
    private PasswordHashService passwordHashService;

    public SignUpServiceImpl(UsersDao usersDao, PasswordHashService passwordHashService) {
        this.usersDao = usersDao;
        this.passwordHashService = passwordHashService;
    }

    @Override
    public UsersEntity signUp(String email, SecureString password, String name,
                              LocalDate birthDate, String phone, String address,
                              UserRole role, BigDecimal reputation) throws SignUpException {
        validateInput(email, password, name, birthDate, phone, address);
        if (userExists(email)) {
            throw new SignUpException("User with the same email already exists.");
        }

        String passwordHash = passwordHashService.getHash(password);
        UsersEntity user = new UsersEntity(0, email, passwordHash, UserRole.CLIENT, name,
                birthDate, LocalDate.now(), false, reputation);
        user.setContactPhone(phone);
        user.setAddress(address);

        usersDao.save(user);
        return user;
    }

    private boolean userExists(String email) {
        //TODO: implement getByEmail() method for UsersDao and uncomment this line.
        //return usersDao.getByEmail(email) != null;
        return true;
    }

    private static final String PHONE_REGEX = "[\\d\\s\\(\\)\\+]+";

    private void validateInput(String email, SecureString password, String name, LocalDate birthDate,
                               String phone, String address) throws SignUpException {
        if (email == null || email.length() == 0) {
            throw new SignUpException("Email not specified.");
        }
        if (password == null) {
            throw new SignUpException("Password is not specified.");
        }
        if (name == null || name.length() == 0) {
            throw new SignUpException("Name is not specified.");
        }
        if (birthDate == null) {
            throw new SignUpException("Birth date is not specified.");
        }
        if (phone == null || phone.length() == 0) {
            throw new SignUpException("Phone number is not specified.");
        }
        if (!phone.matches(PHONE_REGEX)) {
            throw new SignUpException("Phone number may only contain digits, spaces, parentheses and '+' sign.");
        }
        if (address == null || address.length() == 0) {
            throw new SignUpException("Address is not specified.");
        }
    }
}
