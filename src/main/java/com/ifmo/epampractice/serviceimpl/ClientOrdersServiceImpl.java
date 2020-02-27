package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.InvoiceDao;
import com.ifmo.epampractice.dao.OrderDao;
import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.daoimpl.InvoiceDaoImpl;
import com.ifmo.epampractice.daoimpl.OrderDaoImpl;
import com.ifmo.epampractice.daoimpl.UsersDaoImpl;
import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.entity.OrderEntity;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.OrderStatus;
import com.ifmo.epampractice.services.ClientOrdersService;

import java.util.Date;
import java.util.List;

public class ClientOrdersServiceImpl implements ClientOrdersService {
    @Override
    public List<OrderEntity> getAllOrders(int userId) {
        OrderDao dao = new OrderDaoImpl();
        return dao.getAllByUserId(userId);
    }

    @Override
    public List<InvoiceEntity> getAllInvoices(int userId) {
        InvoiceDao dao = new InvoiceDaoImpl();
        return dao.getAllByUserId(userId);
    }

    @Override
    public boolean newOrder(int carId, int clientId, Date rentStartDate, Date rentEndDate) {
        if (accessToCreate(clientId)) {
            OrderEntity order = new OrderEntity();
            order.setCarId(carId);
            order.setClientId(clientId);
            order.setRentStartDate(rentStartDate);
            order.setRentEndDate(rentEndDate);

            OrderDao dao = new OrderDaoImpl();
            dao.save(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelOrder(int orderId) {
        OrderDao dao = new OrderDaoImpl();
        OrderEntity order = dao.get(orderId);
        if (accessToCancel(order)) {
            order.setStatus(OrderStatus.CANCELED);
            dao.update(order);
            return true;
        }
        return false;

    }

    private boolean accessToCancel(OrderEntity order) {
        return (order.getStatus() == OrderStatus.OPEN);
    }

    private boolean accessToCreate(int clientId) {
        UsersDao dao = new UsersDaoImpl();
        UsersEntity user = dao.get(clientId);

        return (user.getDrivingLicenseId() != 0 & user.getPassId() != 0);
    }
}
