package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.OrderEntity;

import java.util.List;

public interface OrderDao extends BasicDaoInterface<OrderEntity> {
    @Override
    List<OrderEntity> getAll();

    @Override
    OrderEntity get(int id);

    @Override
    void update(OrderEntity entity);

    @Override
    void delete(OrderEntity entity);

    @Override
    void save(OrderEntity entity);
}
