package ru.javawebinar.topjava.web.actions.mealActions;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditMealAction implements Action<Meal, Integer> {
    @Override
    public void execute(Dao<Meal, Integer> dao, HttpServletRequest request, HttpServletResponse response) {
        int mealId = Integer.parseInt(request.getParameter("id"));
        Meal meal = dao.read(mealId);
        request.setAttribute("meal", meal);
    }
}
