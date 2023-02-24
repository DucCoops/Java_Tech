package org.example.DAO;

import org.example.POJO.Manufacture;

import java.util.List;

public interface Generic<T> {
    boolean add(T p);
    T get(String id);
    List<T> getAll();
    boolean remove(T p);
    boolean update(T p);
}
