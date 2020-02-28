package com.ifmo.epampractice.services;

import com.ifmo.epampractice.entity.DrivingLicenseEntity;
import com.ifmo.epampractice.entity.PassportEntity;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.UserRole;
import com.ifmo.epampractice.security.SecureString;

import java.time.LocalDate;

public interface PersonalDataService {
    public PassportEntity changePassport (int userId, String issueCountry, String issuer, LocalDate issueDate,
                                          LocalDate expirationDate, String serialNumber);
    public DrivingLicenseEntity changeDrivingLicence (int userId, LocalDate issueDate, LocalDate expirationDate,
                                                      String serialNumber);
    public UsersEntity changeUserData(int id, String email, SecureString password, String name,
                                      String contactPhone, String address);
}
