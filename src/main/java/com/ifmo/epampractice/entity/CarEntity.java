package com.ifmo.epampractice.entity;

import com.ifmo.epampractice.enums.CarStatus;

import java.util.Objects;

public class CarEntity {
    private int         id;
    private int         modelId;
    private String      color;
    private String      regNumber;
    private String      vin;
    private CarStatus   status;

    public CarEntity(int id, int modelId, String color, String regNumber,
                     String vin, CarStatus status) {
        this.id = id;
        this.modelId = modelId;
        this.color = color;
        this.regNumber = regNumber;
        this.vin = vin;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarEntity that = (CarEntity) o;

        return (
                id == that.id &&
                modelId == that.modelId &&
                Objects.equals(color, that.color) &&
                Objects.equals(regNumber, that.regNumber) &&
                Objects.equals(vin, that.vin) &&
                status == that.status
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelId, color, regNumber, vin, status);
    }
}
