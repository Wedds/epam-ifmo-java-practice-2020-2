package com.ifmo.epampractice.dao;
import com.ifmo.epampractice.entity.DrivingLicenseEntity;

import java.util.ArrayList;
import java.util.List;

public interface DrivingLicenseDao extends BasicDaoInterface<DrivingLicenseEntity> {
    @Override
    List<DrivingLicenseEntity> getAll();

    @Override
    DrivingLicenseEntity get(int id);

    @Override
    void update(DrivingLicenseEntity entity);

    @Override
    void delete(DrivingLicenseEntity entity);

    @Override
    void save(DrivingLicenseEntity entity);

    DrivingLicenseEntity getBySerialNumber(String serialNumber);
}
