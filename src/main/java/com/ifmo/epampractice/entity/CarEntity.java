package com.ifmo.epampractice.entity;

import com.ifmo.epampractice.enums.CarStatus;

import java.util.Objects;

public class CarEntity {
    private int         id;
    private int         modelId;
    private String      color;
    private String      regNumber;
    private String      VIN;
    private CarStatus   status;

    public CarEntity(int id, int modelId, String color, String regNumber,
                     String VIN, CarStatus status) {
        this.id = id;
        this.modelId = modelId;
        this.color = color;
        this.regNumber = regNumber;
        this.VIN = VIN;
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

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
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

        CarEntity carEntity = (CarEntity) o;

        return (
                id == carEntity.id &&
                modelId == carEntity.modelId &&
                color.equals(carEntity.color) &&
                regNumber.equals(carEntity.regNumber) &&
                VIN.equals(carEntity.VIN) &&
                status == carEntity.status
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelId, color, regNumber, VIN, status);
    }
}
