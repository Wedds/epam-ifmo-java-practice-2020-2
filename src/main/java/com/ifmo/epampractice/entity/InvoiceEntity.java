package com.ifmo.epampractice.entity;

import java.util.Date;
import java.util.Objects;

public class InvoiceEntity {
    private int id;
    private int orderId;
    private Date issueDate;
    private Date paymentDate;
    private int totalPrice;

    public InvoiceEntity() {
    }

    public InvoiceEntity(int id, int orderId, Date issueDate, Date paymentDate, int totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceEntity)) return false;
        InvoiceEntity that = (InvoiceEntity) o;
        return id == that.id &&
                orderId == that.orderId &&
                totalPrice == that.totalPrice &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, issueDate, paymentDate, totalPrice);
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
