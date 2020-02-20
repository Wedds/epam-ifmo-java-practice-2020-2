package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.UsersEntity;

import java.util.List;

public interface UsersDao extends BasicDaoInterface<UsersEntity> {
    List<UsersEntity> getAll();

    UsersEntity get(int id);

    void update(UsersEntity o);

    void delete(UsersEntity o);

    void save(UsersEntity o);
}
