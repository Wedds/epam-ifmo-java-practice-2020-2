package com.ifmo.epampractice.dao;
import com.ifmo.epampractice.entity.DrivingLicenseEntity;

import java.util.ArrayList;
import java.util.List;

public interface DrivingLicenseDAO extends BasicDaoInterface<DrivingLicenseEntity> {
    @Override
    List<DrivingLicenseEntity> getAll();

    @Override
    DrivingLicenseEntity get(int id);

    @Override
    void update(DrivingLicenseEntity o);

    @Override
    void delete(DrivingLicenseEntity o);

    @Override
    void save(DrivingLicenseEntity o);
}