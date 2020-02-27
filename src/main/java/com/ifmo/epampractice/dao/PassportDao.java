package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.PassportEntity;

import java.util.List;

public interface PassportDao extends BasicDaoInterface<PassportEntity> {
    @Override
    List<PassportEntity> getAll();

    @Override
    PassportEntity get(int id);

    @Override
    void update(PassportEntity PassportEntity);

    @Override
    void delete(PassportEntity PassportEntity);

    @Override
    void save(PassportEntity PassportEntity);

}
