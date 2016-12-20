package ru.javawebinar.topjava.web.actions;

import ru.javawebinar.topjava.dao.Dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmptyAction implements Action {
    @Override
    public void execute(Dao dao, HttpServletRequest request, HttpServletResponse response) {

    }
}
