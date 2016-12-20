package ru.javawebinar.topjava.dao;


import java.io.Serializable;
import java.util.List;

public interface Dao<T, PK extends Serializable> {
    void create(T t);

    T read(PK id);

    void update(T t);

    void delete(PK id);

    List<T> getList();
}
