package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.OrderDao;
import com.ifmo.epampractice.daoimpl.OrderDaoImpl;
import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.entity.OrderEntity;
import com.ifmo.epampractice.services.ClientOrdersService;

import java.util.Date;
import java.util.List;

public class ClientOrdersServiceImpl implements ClientOrdersService {
    @Override
    public List<OrderEntity> getAllOrders(int userId) {
        return null;
    }

    @Override
    public List<InvoiceEntity> getAllInvoices(int userId) {
        return null;
    }

    @Override
    public void newOrder(int carId, int clientId, Date rentStartDate, Date rentEndDate) {

    }
}
