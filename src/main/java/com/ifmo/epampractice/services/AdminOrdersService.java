package com.ifmo.epampractice.services;

import com.ifmo.epampractice.entity.CarDamageEntity;
import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.entity.OrderEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AdminOrdersService {

    public List<OrderEntity> getAllOrders();

    public OrderEntity getOrderById(int orderId);

    public void approveOrder(int orderId);

    public void disapproveOrder(int orderId);

    public boolean userDocumentsIsAvailable(int userId);

    public InvoiceEntity getInvoice(int orderId);

    public InvoiceEntity getInvoice(int orderId, List<InvoiceEntity> invoices);

    public void addInvoice(int orderId, BigDecimal totalPrice);

    public void updateInvoice(int orderId, BigDecimal totalPrice);

    public CarDamageEntity getCarDamage(int orderId);

    public CarDamageEntity getCarDamage(int orderId, List<CarDamageEntity> carDamages);

    public void addCarDamage(int orderId, String description, LocalDate accidentDate, int severity, BigDecimal repairCost);

    public void updateCarDamage(int orderId, String description, LocalDate accidentDate, int severity, BigDecimal repairCost);

}
