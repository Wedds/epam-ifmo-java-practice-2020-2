package com.ifmo.epampractice.entity;

import com.ifmo.epampractice.enums.OrderStatus;

import java.util.Date;
import java.util.Objects;

public class OrderEntity {

    private int id;
    private int carId;
    private int clientId;
    private int adminId;
    private OrderStatus status;
    private Date rentStartDate;
    private Date rentEndDate;
    private int discount;

    public OrderEntity(){}

    public OrderEntity(int id, int carId, int clientId, int adminId, OrderStatus status, Date rentStartDate, Date rentEndDate, int discount) {
        this.id = id;
        this.carId = carId;
        this.clientId = clientId;
        this.adminId = adminId;
        this.status = status;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        OrderEntity orders = (OrderEntity) o;
        return id == orders.id &&
                carId == orders.carId &&
                clientId == orders.clientId &&
                adminId == orders.adminId &&
                discount == orders.discount &&
                status == orders.status &&
                Objects.equals(rentStartDate, orders.rentStartDate) &&
                Objects.equals(rentEndDate, orders.rentEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carId, clientId, adminId, status, rentStartDate, rentEndDate, discount);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
