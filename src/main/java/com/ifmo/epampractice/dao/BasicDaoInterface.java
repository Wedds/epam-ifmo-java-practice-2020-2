package com.ifmo.epampractice.dao;

import java.util.List;

public interface BasicDaoInterface<T> {
    List<T> getAll();

    T get(int id);

    void update(T o);

    void delete(T o);
}
