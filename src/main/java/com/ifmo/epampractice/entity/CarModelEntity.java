package com.ifmo.epampractice.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class CarModelEntity {
    private int         id;
    private String      brandName;
    private String      modelName;
    private BigDecimal  pricePerHour;

    public CarModelEntity(int id, String brandName, String modelName, BigDecimal pricePerHour) {
        this.id = id;
        this.brandName = brandName;
        this.modelName = modelName;
        this.pricePerHour = pricePerHour;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarModelEntity that = (CarModelEntity) o;

        return (
                id == that.id &&
                Objects.equals(brandName, that.brandName) &&
                Objects.equals(modelName, that.modelName) &&
                Objects.equals(pricePerHour, that.pricePerHour)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName, modelName, pricePerHour);
    }
}
