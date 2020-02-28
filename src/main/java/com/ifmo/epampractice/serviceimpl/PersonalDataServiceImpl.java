package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.entity.DrivingLicenseEntity;
import com.ifmo.epampractice.entity.PassportEntity;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.services.PasswordHashService;
import com.ifmo.epampractice.services.PersonalDataService;
import com.ifmo.epampractice.security.SecureString;

import java.time.LocalDate;

public class PersonalDataServiceImpl implements PersonalDataService {

    private UsersDao usersDao;
   // private PassportDao passportDao; after merge
    private PasswordHashService passwordHashService;

    @Override
    public PassportEntity changePassport(int userId, String issueCountry, String issuer, LocalDate issueDate,
                                         LocalDate expirationDate, String serialNumber) {
        UsersEntity usersEntity = usersDao.get(userId);
        //PassportEntity passportEntity = passportDao.get(usersEntity.getPassId()); after merge
        if (usersEntity == null){
            throw new RuntimeException();
        }
        if (passportEntity == null){
            return createPassport(usersEntity, issueCountry, issuer, issueDate, expirationDate, serialNumber);
        }
        else {
            return updatePassport(passportEntity, issueCountry, issuer, issueDate, expirationDate, serialNumber);
        }


    }

    private PassportEntity createPassport(UsersEntity usersEntity, String issueCountry, String issuer, LocalDate issueDate,
                                          LocalDate expirationDate, String serialNumber){
        validatePassport(issueCountry, issuer, issueDate, expirationDate, serialNumber);

        PassportEntity passportEntity = new PassportEntity(0,issueCountry, issuer, issueDate, expirationDate,
                serialNumber);
        //passportDAO.save(passportEntity); after merge
        //add getBySerialNumber in PassportDao to get Id for new passport
        //update userEntity with Id of new passport
        //return passportEntity
        return null;
    }
    private PassportEntity updatePassport(PassportEntity passportEntity, String issueCountry, String issuer, LocalDate issueDate,
                                          LocalDate expirationDate, String serialNumber){
        validatePassport(issueCountry, issuer, issueDate, expirationDate, serialNumber);
        //passportEntity.set set all fields
        //update passportEntity
        //return passportEntity
        return null;
    }
    @Override
    public DrivingLicenseEntity changeDrivingLicence(int userId, LocalDate issueDate, LocalDate expirationDate,
                                                     String serialNumber) {
        //here will be two methods to create drivingLicenseEntity and to update it
        //need method to get drivingLicenseEntity by serial number in Dao
        //need method to validate data
        //return drivingLicenceEntity
        return null;
    }

    @Override
    public UsersEntity changeUserData(int id, String email, SecureString password, String name,
                                      String contactPhone, String address) {
        UsersEntity usersEntity = usersDao.get(id);
        validateUserData(email, password, name, contactPhone, address);
        usersEntity.setEmail(email);
        String passwordHash = passwordHashService.getHash(password);
        usersEntity.setPasswordHash(passwordHash);
        usersEntity.setName(name);
        usersEntity.setContactPhone(contactPhone);
        usersEntity.setAddress(address);
        usersDao.update(usersEntity);
        return usersEntity;
    }

    private static final String PHONE_REGEX = "[\\d\\s\\(\\)\\+]+";

    private void validateUserData(String email, SecureString password, String name,
                               String phone, String address) throws RuntimeException {
        if (email == null || email.length() == 0) {
            throw new RuntimeException("Email not specified.");
        }
        if (password == null) {
            throw new RuntimeException("Password is not specified.");
        }
        if (name == null || name.length() == 0) {
            throw new RuntimeException("Name is not specified.");
        }
        if (phone == null || phone.length() == 0) {
            throw new RuntimeException("Phone number is not specified.");
        }
        if (!phone.matches(PHONE_REGEX)) {
            throw new RuntimeException("Phone number may only contain digits, spaces, parentheses and '+' sign.");
        }
        if (address == null || address.length() == 0) {
            throw new RuntimeException("Address is not specified.");
        }
    }
    private void validatePassport (String issueCountry, String issuer, LocalDate issueDate,
                                   LocalDate expirationDate, String serialNumber) throws RuntimeException {
        if (issueCountry == null || issueCountry.length() == 0) {
            throw new RuntimeException("IssueCountry not specified.");
        }
        if (issuer == null || issuer.length() == 0) {
            throw new RuntimeException("Issuer is not specified.");
        }
        if (issueDate == null) {
            throw new RuntimeException("IssueDate date is not specified.");
        }
        if (expirationDate == null) {
            throw new RuntimeException("ExpirationDate date is not specified.");
        }
        if (serialNumber == null || serialNumber.length() == 0) {
            throw new RuntimeException("SerialNumber number is not specified.");
        }
    }
}
