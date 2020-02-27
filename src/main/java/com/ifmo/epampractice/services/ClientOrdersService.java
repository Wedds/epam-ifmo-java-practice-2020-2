package com.ifmo.epampractice.services;

import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ClientOrdersService {
    List<OrderEntity> getAllOrders(int userId);
    List<InvoiceEntity> getAllInvoices(int userId);
    InvoiceEntity getInvoiceByOrderId(int orderId);

    boolean newOrder(int carId, int clientId, Date rentStartDate, Date rentEndDate);
    boolean cancelOrder(int orderId);

    BigDecimal calcCost(int orderId);
}
