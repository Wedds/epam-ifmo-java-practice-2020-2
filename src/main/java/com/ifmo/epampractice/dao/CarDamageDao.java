package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.CarDamageEntity;

import java.util.List;

public interface CarDamageDao extends BasicDaoInterface<CarDamageEntity> {

    @Override
    List<CarDamageEntity> getAll();

    @Override
    CarDamageEntity get(int id);

    @Override
    void update(CarDamageEntity carDamageEntity);

    @Override
    void delete(CarDamageEntity carDamageEntity);

    @Override
    void save(CarDamageEntity carDamageEntity);
}
