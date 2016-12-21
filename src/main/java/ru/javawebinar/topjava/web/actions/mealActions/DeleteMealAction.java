package ru.javawebinar.topjava.web.actions.mealActions;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.web.actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;


public class DeleteMealAction implements Action {
    @Override
    public void execute(Dao dao, HttpServletRequest request, HttpServletResponse response) {
        int mealId = Integer.parseInt(request.getParameter("id"));
        dao.delete(mealId);
        List<MealWithExceed> mealWithExceeds = getFilteredWithExceeded(dao.getList(),
                LocalTime.MIN, LocalTime.MAX, DEFAULT_CALORIES_PER_DAY);
        request.setAttribute("meals", mealWithExceeds);
    }
}
