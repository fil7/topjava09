package ru.javawebinar.topjava.web.actions;

import ru.javawebinar.topjava.dao.Dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public interface Action<T, PK extends Serializable> {
    void execute(Dao<T, PK> dao, HttpServletRequest request, HttpServletResponse response);


}
