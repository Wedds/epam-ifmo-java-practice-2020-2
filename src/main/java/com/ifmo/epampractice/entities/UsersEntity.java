package com.ifmo.epampractice.entities;

import com.ifmo.epampractice.enums.UserRole;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsersEntity {
    private int id;
    private String email;
    private String passwordHash;
    private UserRole role;
    private String name;
    private LocalDate birthDate;
    private LocalDateTime signUpDate;
    private int passId;
    private int drivingLicenseId;
    private String contactPhone;
    private String address;
    private boolean isBlocked;
    private BigDecimal reputation;

    public UsersEntity(int id, String email, String passwordHash, UserRole role, String name,
                       LocalDate birthDate, LocalDateTime signUpDate, int passId,
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
                       LocalDate birthDate, LocalDateTime signUpDate, boolean isBlocked,
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

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDateTime signUpDate) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (passId != that.passId) return false;
        if (drivingLicenseId != that.drivingLicenseId) return false;
        if (isBlocked != that.isBlocked) return false;
        if (!email.equals(that.email)) return false;
        if (!passwordHash.equals(that.passwordHash)) return false;
        if (role != that.role) return false;
        if (!name.equals(that.name)) return false;
        if (!birthDate.equals(that.birthDate)) return false;
        if (!signUpDate.equals(that.signUpDate)) return false;
        if (!contactPhone.equals(that.contactPhone)) return false;
        if (!address.equals(that.address)) return false;
        return reputation.equals(that.reputation);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + passwordHash.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + birthDate.hashCode();
        result = 31 * result + signUpDate.hashCode();
        result = 31 * result + passId;
        result = 31 * result + drivingLicenseId;
        result = 31 * result + contactPhone.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + (isBlocked ? 1 : 0);
        result = 31 * result + reputation.hashCode();
        return result;
    }
}
