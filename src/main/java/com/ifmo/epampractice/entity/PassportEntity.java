package com.ifmo.epampractice.entity;

import java.time.LocalDate;
import java.util.Objects;

public class PassportEntity {
    private int id;
    private String issueCountry;
    private String issuer;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String serialNumber;

    public PassportEntity(int id, String issueCountry, String issuer, LocalDate issueDate,
                          LocalDate expirationDate, String serialNumber) {
        this.id = id;
        this.issueCountry = issueCountry;
        this.issuer = issuer;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssueCountry() {
        return issueCountry;
    }

    public void setIssueCountry(String issueCountry) {
        this.issueCountry = issueCountry;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassportEntity that = (PassportEntity) o;
        return id == that.id &&
                Objects.equals(issueCountry,that.issueCountry) &&
                Objects.equals(issuer, that.issuer) &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueCountry, issuer, issueDate, expirationDate, serialNumber);
    }
}
