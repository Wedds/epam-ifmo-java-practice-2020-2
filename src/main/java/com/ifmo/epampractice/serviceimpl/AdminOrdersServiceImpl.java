package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.CarDamageDao;
import com.ifmo.epampractice.dao.InvoiceDao;
import com.ifmo.epampractice.dao.OrderDao;
import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.daoimpl.CarDamageDaoImpl;
import com.ifmo.epampractice.daoimpl.InvoiceDaoImpl;
import com.ifmo.epampractice.daoimpl.OrderDaoImpl;
import com.ifmo.epampractice.daoimpl.UsersDaoImpl;
import com.ifmo.epampractice.entity.CarDamageEntity;
import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.entity.OrderEntity;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.DamageStatus;
import com.ifmo.epampractice.enums.InvoiceStatus;
import com.ifmo.epampractice.enums.OrderStatus;
import com.ifmo.epampractice.services.AdminOrdersService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AdminOrdersServiceImpl implements AdminOrdersService {

    @Override
    public List<OrderEntity> getAllOrders() {
        OrderDao dao = new OrderDaoImpl();
        return dao.getAll();
    }

    @Override
    public OrderEntity getOrderById(int orderId) {
        OrderDao orderDao = new OrderDaoImpl();
        return orderDao.get(orderId);
    }

    @Override
    public void approveOrder(int orderId) {
        OrderDao orderDao = new OrderDaoImpl();
        OrderEntity orderEntity = orderDao.get(orderId);
        orderEntity.setStatus(OrderStatus.APPROVED);
        orderDao.update(orderEntity);
    }

    @Override
    public void disapproveOrder(int orderId) {
        OrderDao orderDao = new OrderDaoImpl();
        OrderEntity orderEntity = orderDao.get(orderId);
        orderEntity.setStatus(OrderStatus.DENIED);
        orderDao.update(orderEntity);
    }

    @Override
    public boolean userDocumentsIsAvailable(int userId) {
        UsersDao usersDao = new UsersDaoImpl();
        UsersEntity usersEntity = usersDao.get(userId);
        return usersEntity.getDrivingLicenseId() != 0 && usersEntity.getPassId() != 0;
    }

    @Override
    public InvoiceEntity getInvoice(int orderId) {
        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        return invoiceDao.getByOrderId(orderId);
    }

    @Override
    public void addInvoice(int orderId, BigDecimal totalPrice) {
        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setOrderId(orderId);
        invoiceEntity.setTotalPrice(totalPrice);
        invoiceEntity.setIssueDate(new Date());
        invoiceEntity.setStatus(InvoiceStatus.OPEN);
        invoiceDao.save(invoiceEntity);

        OrderDao orderDao = new OrderDaoImpl();
        OrderEntity orderEntity = orderDao.get(orderId);
        orderEntity.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
        orderDao.update(orderEntity);
    }

    @Override
    public void updateInvoice(int orderId, BigDecimal totalPrice) {
        InvoiceDao invoiceDao = new InvoiceDaoImpl();
        InvoiceEntity invoiceEntity = invoiceDao.getByOrderId(orderId);
        if (invoiceEntity.getStatus() == InvoiceStatus.OPEN) {
            invoiceEntity.setTotalPrice(totalPrice);
            invoiceEntity.setIssueDate(new Date());
        }
        invoiceDao.update(invoiceEntity);
    }

    @Override
    public CarDamageEntity getCarDamage(int orderId) {
        CarDamageDao carDamageDao = new CarDamageDaoImpl();
        return carDamageDao.getByOrderId(orderId);
    }

    @Override
    public void addCarDamage(int orderId, String description, LocalDate accidentDate,
                             int severity, BigDecimal repairCost) {
        CarDamageDao carDamageDao = new CarDamageDaoImpl();
        CarDamageEntity carDamageEntity = new CarDamageEntity(0, orderId, description,
                accidentDate, severity, repairCost, DamageStatus.NOT_FIXED);

        carDamageDao.save(carDamageEntity);
    }

    @Override
    public void updateCarDamage(int orderId, String description, LocalDate accidentDate,
                                int severity, BigDecimal repairCost) {
        CarDamageDao carDamageDao = new CarDamageDaoImpl();
        CarDamageEntity carDamageEntity = carDamageDao.getByOrderId(orderId);
        carDamageEntity.setDescription(description);
        carDamageEntity.setAccidentDate(accidentDate);
        carDamageEntity.setSeverity(severity);
        carDamageEntity.setRepairCost(repairCost);

        carDamageDao.update(carDamageEntity);
    }
}
