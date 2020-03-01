package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.daoimpl.UsersDaoImpl;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.security.SecureString;
import com.ifmo.epampractice.services.LogInService;
import com.ifmo.epampractice.services.PasswordHashService;

public class LogInServiceImpl implements LogInService {
    @Override
    public boolean getAuth(String login, SecureString password) {
        UsersDao dao = new UsersDaoImpl();
        UsersEntity user = dao.getByEmail(login);
        if(user == null){
            return false;
        }
        PasswordHashService passwordHashService = new PasswordHashServiceImpl();
        String expectedPasswordHash = passwordHashService.getHash(password);
        return expectedPasswordHash.equals(user.getPasswordHash());
    }
}
