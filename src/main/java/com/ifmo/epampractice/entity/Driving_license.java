package com.ifmo.epampractice.entity;

import java.util.Date;
import java.util.Objects;

public class Driving_license {

    private int id;
    private Date Issue_date;
    private Date Expiration_date;
    private String Serial_number;


    public Driving_license(int id, Date Issue_date, Date Expiration_date, String Serial_number) {
        this.id = id;
        this.Issue_date = Issue_date;
        this.Expiration_date = Expiration_date;
        this.Serial_number = Serial_number;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getIssue_date() { return Issue_date; }

    public void setIssue_date(Date issue_date) { Issue_date = issue_date; }

    public Date getExpiration_date() { return Expiration_date; }

    public void setExpiration_date(Date expiration_date) { Expiration_date = expiration_date; }

    public String getSerial_number() { return Serial_number; }

    public void setSerial_number(String serial_number) { Serial_number = serial_number; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driving_license)) return false;

        Driving_license that = (Driving_license) o;

        if (getId() != that.getId()) return false;
        if (!getIssue_date().equals(that.getIssue_date())) return false;
        if (!getExpiration_date().equals(that.getExpiration_date())) return false;
        return getSerial_number().equals(that.getSerial_number());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Issue_date, Expiration_date, Serial_number);
    }

}
