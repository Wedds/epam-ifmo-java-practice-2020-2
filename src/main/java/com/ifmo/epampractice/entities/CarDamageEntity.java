package com.ifmo.epampractice.entities;

import com.ifmo.epampractice.enums.DamageStatus;

import java.time.LocalDate;
import java.util.Objects;

public class CarDamageEntity {
    private int id;
    private int orderId;
    private String description;
    private LocalDate accidentDate;
    private int severity;
    private int repairCost;
    private DamageStatus status;

    public CarDamageEntity(int id, int orderId, String description, LocalDate accidentDate, int severity, int repairCost, DamageStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.description = description;
        this.accidentDate = accidentDate;
        this.severity = severity;
        this.repairCost = repairCost;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(LocalDate accidentDate) {
        this.accidentDate = accidentDate;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public DamageStatus getStatus() {
        return status;
    }

    public void setStatus(DamageStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDamageEntity that = (CarDamageEntity) o;
        return id == that.id &&
                orderId == that.orderId &&
                severity == that.severity &&
                repairCost == that.repairCost &&
                description.equals(that.description) &&
                accidentDate.equals(that.accidentDate) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, description, accidentDate, severity, repairCost, status);
    }
}
