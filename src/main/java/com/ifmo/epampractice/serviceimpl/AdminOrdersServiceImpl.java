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
import java.util.List;

public class AdminOrdersServiceImpl implements AdminOrdersService {
    private OrderDao orderDao = new OrderDaoImpl();
    private UsersDao usersDao = new UsersDaoImpl();
    private InvoiceDao invoiceDao = new InvoiceDaoImpl();
    private CarDamageDao carDamageDao = new CarDamageDaoImpl();

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderDao.getAll();
    }

    @Override
    public OrderEntity getOrderById(int orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public void approveOrder(int orderId) {
        OrderEntity orderEntity = orderDao.get(orderId);
        orderEntity.setStatus(OrderStatus.APPROVED);
        orderDao.update(orderEntity);
    }

    @Override
    public void disapproveOrder(int orderId) {
        OrderEntity orderEntity = orderDao.get(orderId);
        orderEntity.setStatus(OrderStatus.DENIED);
        orderDao.update(orderEntity);
    }

    @Override
    public boolean userDocumentsIsAvailable(int userId) {
        UsersEntity usersEntity = usersDao.get(userId);
        return usersEntity.getDrivingLicenseId() != 0 && usersEntity.getPassId() != 0;
    }

    @Override
    public InvoiceEntity getInvoice(int orderId) {
        return invoiceDao.getByOrderId(orderId);
    }

    @Override
    public InvoiceEntity getInvoice(int orderId, List<InvoiceEntity> invoices) {
        for (InvoiceEntity invoiceEntity : invoices) {
            if (invoiceEntity.getOrderId() == orderId) {
                return invoiceEntity;
            }
        }
        return null;
    }

    @Override
    public void addInvoice(int orderId, BigDecimal totalPrice) {
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setOrderId(orderId);
        invoiceEntity.setTotalPrice(totalPrice);
        invoiceEntity.setIssueDate(LocalDate.now());
        invoiceEntity.setStatus(InvoiceStatus.OPEN);
        invoiceDao.save(invoiceEntity);

        OrderEntity orderEntity = orderDao.get(orderId);
        orderEntity.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
        orderDao.update(orderEntity);
    }

    @Override
    public void updateInvoice(int orderId, BigDecimal totalPrice) {
        InvoiceEntity invoiceEntity = invoiceDao.getByOrderId(orderId);
        if (invoiceEntity.getStatus() == InvoiceStatus.OPEN) {
            invoiceEntity.setTotalPrice(totalPrice);
            invoiceEntity.setIssueDate(LocalDate.now());
        }
        invoiceDao.update(invoiceEntity);
    }

    @Override
    public CarDamageEntity getCarDamage(int orderId) {
        return carDamageDao.getByOrderId(orderId);
    }

    @Override
    public CarDamageEntity getCarDamage(int orderId, List<CarDamageEntity> carDamages) {
        for (CarDamageEntity carDamageEntity : carDamages) {
            if (carDamageEntity.getOrderId() == orderId) {
                return carDamageEntity;
            }
        }
        return null;
    }

    @Override
    public void addCarDamage(int orderId, String description, LocalDate accidentDate,
                             int severity, BigDecimal repairCost) {
        CarDamageEntity carDamageEntity = new CarDamageEntity(0, orderId, description,
                accidentDate, severity, repairCost, DamageStatus.NOT_FIXED);

        carDamageDao.save(carDamageEntity);
    }

    @Override
    public void updateCarDamage(int orderId, String description, LocalDate accidentDate,
                                int severity, BigDecimal repairCost) {
        CarDamageEntity carDamageEntity = carDamageDao.getByOrderId(orderId);
        carDamageEntity.setDescription(description);
        carDamageEntity.setAccidentDate(accidentDate);
        carDamageEntity.setSeverity(severity);
        carDamageEntity.setRepairCost(repairCost);

        carDamageDao.update(carDamageEntity);
    }
}
