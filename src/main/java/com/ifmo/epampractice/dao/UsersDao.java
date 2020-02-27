package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.UsersEntity;

import java.util.List;

public interface UsersDao extends BasicDaoInterface<UsersEntity> {
    List<UsersEntity> getAll();

    UsersEntity get(int id);

    UsersEntity getByEmail(String email);

    void update(UsersEntity entity);

    void delete(UsersEntity entity);

    void save(UsersEntity entity);
}
