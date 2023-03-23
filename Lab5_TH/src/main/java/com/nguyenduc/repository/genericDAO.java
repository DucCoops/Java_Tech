package com.nguyenduc.repository;

import java.util.List;

public interface genericDAO<T> {
    boolean add(T t);
    T get(int id);
    List<T> getAll();
    boolean remove(String id);
    boolean remove(T t);
    boolean update(T t);
    List<T> query(String hql, Object... o);
}
