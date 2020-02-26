package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.InvoiceEntity;

import java.util.List;

public interface InvoiceDao extends BasicDaoInterface<InvoiceEntity> {
    @Override
    List<InvoiceEntity> getAll();

    @Override
    InvoiceEntity get(int id);

    @Override
    void update(InvoiceEntity entity);

    @Override
    void delete(InvoiceEntity entity);

    @Override
    void save(InvoiceEntity entity);

    List<InvoiceEntity> getAllByUserId(int userId);
}
