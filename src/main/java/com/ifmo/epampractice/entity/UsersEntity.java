package com.ifmo.epampractice.entity;

import com.ifmo.epampractice.enums.UserRole;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UsersEntity {

    private int id;
    private String email;
    private String passwordHash;
    private UserRole role;
    private String name;
    private LocalDate birthDate;
    private LocalDate signUpDate;
    private int passId;
    private int drivingLicenseId;
    private String contactPhone;
    private String address;
    private boolean isBlocked;
    private BigDecimal reputation;

    public UsersEntity(int id, String email, String passwordHash, UserRole role, String name,
                       LocalDate birthDate, LocalDate signUpDate, int passId,
                       int drivingLicenseId, String contactPhone, String address,
                       boolean isBlocked, BigDecimal reputation) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.name = name;
        this.birthDate = birthDate;
        this.signUpDate = signUpDate;
        this.passId = passId;
        this.drivingLicenseId = drivingLicenseId;
        this.contactPhone = contactPhone;
        this.address = address;
        this.isBlocked = isBlocked;
        this.reputation = reputation;
    }

    public UsersEntity(int id, String email, String passwordHash, UserRole role, String name,
                       LocalDate birthDate, LocalDate signUpDate, boolean isBlocked,
                       BigDecimal reputation) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.name = name;
        this.birthDate = birthDate;
        this.signUpDate = signUpDate;
        this.isBlocked = isBlocked;
        this.reputation = reputation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

    public int getPassId() {
        return passId;
    }

    public void setPassId(int passId) {
        this.passId = passId;
    }

    public int getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(int drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public BigDecimal getReputation() {
        return reputation;
    }

    public void setReputation(BigDecimal reputation) {
        this.reputation = reputation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsersEntity that = (UsersEntity) o;

        return id == that.id &&
               passId == that.passId &&
               drivingLicenseId == that.drivingLicenseId &&
               isBlocked == that.isBlocked &&
               Objects.equals(email, that.email) &&
               Objects.equals(passwordHash, that.passwordHash) &&
               role == that.role &&
               Objects.equals(name, that.name) &&
               Objects.equals(birthDate, that.birthDate) &&
               Objects.equals(signUpDate, that.signUpDate) &&
               Objects.equals(contactPhone, that.contactPhone) &&
               Objects.equals(address, that.address) &&
               Objects.equals(reputation, that.reputation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, passwordHash, role, name, birthDate, signUpDate, passId,
                            drivingLicenseId, contactPhone, address, isBlocked, reputation);
    }
}
