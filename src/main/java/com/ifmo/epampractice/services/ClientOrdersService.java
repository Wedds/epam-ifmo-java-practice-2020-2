package com.ifmo.epampractice.services;

import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.entity.OrderEntity;

import java.util.Date;
import java.util.List;

public interface ClientOrdersService {
    List<OrderEntity> getAllOrders(int userId);
    List<InvoiceEntity> getAllInvoices(int userId);
    boolean newOrder(int carId, int clientId, Date rentStartDate, Date rentEndDate);
    boolean cancelOrder(int orderId);
}
