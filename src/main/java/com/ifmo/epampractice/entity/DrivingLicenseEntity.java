package com.ifmo.epampractice.entity;

import java.time.LocalDate;
import java.util.Objects;

public class DrivingLicenseEntity {

    private int id;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String serialNumber;

    public DrivingLicenseEntity(int id, LocalDate issueDate, LocalDate expirationDate, String serialNumber) {
        this.id = id;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.serialNumber = serialNumber;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public LocalDate getIssueDate() { return issueDate; }

    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getExpirationDate() { return expirationDate; }

    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public String getSerialNumber() { return serialNumber; }

    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof DrivingLicenseEntity)) { return false; }

        DrivingLicenseEntity that = (DrivingLicenseEntity) o;

        return (
                id == that.id &&
                        Objects.equals(issueDate, that.issueDate) &&
                        Objects.equals(expirationDate, that.expirationDate) &&
                        Objects.equals(serialNumber, that.serialNumber)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueDate, expirationDate, serialNumber);
    }

}
