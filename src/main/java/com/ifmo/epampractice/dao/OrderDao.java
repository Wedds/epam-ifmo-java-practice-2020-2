package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.OrderEntity;
import com.ifmo.epampractice.enums.OrderStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface OrderDao extends BasicDaoInterface<OrderEntity> {
    @Override
    List<OrderEntity> getAll();

    @Override
    OrderEntity get(int id);

    @Override
    void update(OrderEntity o);

    @Override
    void delete(OrderEntity o);

    @Override
    void save(OrderEntity o);
}
