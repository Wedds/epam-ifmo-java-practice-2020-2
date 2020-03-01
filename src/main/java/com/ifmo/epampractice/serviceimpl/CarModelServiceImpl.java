package com.ifmo.epampractice.serviceimpl;

import com.ifmo.epampractice.dao.CarModelDao;
import com.ifmo.epampractice.daoimpl.CarModelDaoImpl;
import com.ifmo.epampractice.entity.CarModelEntity;
import com.ifmo.epampractice.services.CarModelService;

import java.util.List;

public class CarModelServiceImpl implements CarModelService {

    @Override
    public List<CarModelEntity> getCarModels() {
        CarModelDao dao = new CarModelDaoImpl();
        return dao.getAll();
    }
}
