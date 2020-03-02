package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.DrivingLicenseDao;
import com.ifmo.epampractice.dao.PassportDao;
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
    private PassportDao passportDao;
    private DrivingLicenseDao drivingLicenseDao;
    private PasswordHashService passwordHashService;

    public PersonalDataServiceImpl(UsersDao usersDao, PassportDao passportDao, PasswordHashService passwordHashService){
        this.usersDao = usersDao;
        this.passportDao = passportDao;
        this.drivingLicenseDao = drivingLicenseDao;
        this.passwordHashService = passwordHashService;
    }

    @Override
    public PassportEntity changePassport(int userId, String issueCountry, String issuer, LocalDate issueDate,
                                         LocalDate expirationDate, String serialNumber) {
        UsersEntity usersEntity = usersDao.get(userId);
        PassportEntity passportEntity = passportDao.get(usersEntity.getPassId());
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

    @Override
    public DrivingLicenseEntity changeDrivingLicence(int userId, LocalDate issueDate, LocalDate expirationDate,
                                                     String serialNumber) {

        UsersEntity usersEntity = usersDao.get(userId);
        DrivingLicenseEntity drivingLicenseEntity = drivingLicenseDao.get(usersEntity.getDrivingLicenseId());
        if (usersEntity == null){
            throw new RuntimeException();
        }
        if (drivingLicenseEntity == null){
            return createDrivingLicense(usersEntity, issueDate, expirationDate, serialNumber);
        }
        else {
            return updateDrivingLicense(drivingLicenseEntity, issueDate, expirationDate, serialNumber);
        }
        //need method to validate data
        //return drivingLicenceEntity
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

    private PassportEntity createPassport(UsersEntity usersEntity, String issueCountry, String issuer, LocalDate issueDate,
                                          LocalDate expirationDate, String serialNumber){
        validatePassport(issueCountry, issuer, issueDate, expirationDate, serialNumber);

        PassportEntity passportEntity = new PassportEntity(0, issueCountry, issuer, issueDate, expirationDate,
                serialNumber);
        passportDao.save(passportEntity);
        passportEntity = passportDao.getBySerialNumber(passportEntity.getSerialNumber());
        usersEntity.setPassId(passportEntity.getId());
        usersDao.update(usersEntity);
        return passportEntity;
    }
    private PassportEntity updatePassport(PassportEntity passportEntity, String issueCountry, String issuer,
                                          LocalDate issueDate, LocalDate expirationDate, String serialNumber){
        validatePassport(issueCountry, issuer, issueDate, expirationDate, serialNumber);
        passportEntity.setIssueCountry(issueCountry);
        passportEntity.setIssuer(issuer);
        passportEntity.setIssueDate(issueDate);
        passportEntity.setExpirationDate(expirationDate);
        passportEntity.setSerialNumber(serialNumber);
        passportDao.update(passportEntity);
        return passportEntity;
    }

    private DrivingLicenseEntity createDrivingLicense(UsersEntity usersEntity, LocalDate issueDate,
                                                      LocalDate expirationDate, String serialNumber){
        validateDrivingLicense(issueDate, expirationDate, serialNumber);
        DrivingLicenseEntity drivingLicenseEntity = new DrivingLicenseEntity(0, issueDate, expirationDate,
                serialNumber);
        drivingLicenseDao.save(drivingLicenseEntity);
        drivingLicenseEntity = drivingLicenseDao.getBySerialNumber(drivingLicenseEntity.getSerialNumber());
        usersEntity.setDrivingLicenseId(drivingLicenseEntity.getId());
        usersDao.update(usersEntity);
        return drivingLicenseEntity;
    }

    private DrivingLicenseEntity updateDrivingLicense (DrivingLicenseEntity drivingLicenseEntity, LocalDate issueDate,
                                                       LocalDate expirationDate, String serialNumber){
        validateDrivingLicense(issueDate, expirationDate, serialNumber);
        drivingLicenseEntity.setIssueDate(issueDate);
        drivingLicenseEntity.setExpirationDate(expirationDate);
        drivingLicenseEntity.setSerialNumber(serialNumber);
        drivingLicenseDao.update(drivingLicenseEntity);
        return drivingLicenseEntity;
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

    private void  validateDrivingLicense(LocalDate issueDate, LocalDate expirationDate, String serialNumber){
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
