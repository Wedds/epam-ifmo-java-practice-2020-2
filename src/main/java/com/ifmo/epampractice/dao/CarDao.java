package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.CarEntity;

import java.util.List;

public interface CarDao extends BasicDaoInterface<CarEntity> {
    @Override
    List<CarEntity> getAll();

    @Override
    CarEntity get(int id);

    @Override
    void update(CarEntity carEntity);

    @Override
    void delete(CarEntity carEntity);

    @Override
    void save(CarEntity carEntity);
}
