package ru.javawebinar.topjava.web.actions.mealActions;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.web.actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMealAction implements Action {
    @Override
    public void execute(Dao dao, HttpServletRequest request, HttpServletResponse response) {
        int mealId = Integer.parseInt(request.getParameter("id"));
        dao.delete(mealId);
        request.setAttribute("meals", dao.getList());
    }
}
