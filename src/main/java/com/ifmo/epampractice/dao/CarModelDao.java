package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.CarModelEntity;

import java.util.List;

public interface CarModelDao extends BasicDaoInterface<CarModelEntity> {
    @Override
    List<CarModelEntity> getAll();

    @Override
    CarModelEntity get(int id);

    @Override
    void update(CarModelEntity carModelEntity);

    @Override
    void delete(CarModelEntity carModelEntity);

    @Override
    void save(CarModelEntity carModelEntity);
}
