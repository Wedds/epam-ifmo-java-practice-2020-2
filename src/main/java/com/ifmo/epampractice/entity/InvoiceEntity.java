package com.ifmo.epampractice.entity;

import com.ifmo.epampractice.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class InvoiceEntity {
    private int id;
    private int orderId;
    private Date issueDate;
    private Date paymentDate;
    private BigDecimal totalPrice;
    private InvoiceStatus status;

    public InvoiceEntity() {
    }

    public InvoiceEntity(int id, int orderId, Date issueDate, Date paymentDate, BigDecimal totalPrice, InvoiceStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.issueDate = issueDate;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceEntity)) {
            return false;
        }
        InvoiceEntity that = (InvoiceEntity) o;
        return id == that.id &&
                orderId == that.orderId &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(paymentDate, that.paymentDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, issueDate, paymentDate, totalPrice, status);
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
}
