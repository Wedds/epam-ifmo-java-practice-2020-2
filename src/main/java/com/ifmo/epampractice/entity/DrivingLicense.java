package com.ifmo.epampractice.entity;

import java.util.Date;
import java.util.Objects;

public class DrivingLicense {

    private int id;
    private Date issueDate;
    private Date expirationDate;
    private String serialNumber;

    public DrivingLicense(int id, Date issueDate, Date expirationDate, String serialNumber) {
        this.id = id;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.serialNumber = serialNumber;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getIssueDate() { return issueDate; }

    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public Date getExpirationDate() { return expirationDate; }

    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    public String getSerialNumber() { return serialNumber; }

    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof DrivingLicense)) { return false; }

        DrivingLicense that = (DrivingLicense) o;

        if (getId() != that.getId()) { return false; }
        if (!getIssueDate().equals(that.getIssueDate())) { return false; }
        if (!getExpirationDate().equals(that.getExpirationDate())) { return false; }
        if (!getSerialNumber().equals(that.getSerialNumber())) { return false; }
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
