package ru.javawebinar.topjava.web.actions.mealActions;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.web.actions.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;


public class GetMealListWithExceedAction implements Action<Meal,Integer> {
    @Override
    public void execute(Dao<Meal, Integer> dao, HttpServletRequest request, HttpServletResponse response) {
        List<MealWithExceed> mealWithExceeds = getFilteredWithExceeded(
                dao.getList(),
                LocalTime.MIN,
                LocalTime.MAX,
                DEFAULT_CALORIES_PER_DAY
        );
        Collections.sort(mealWithExceeds, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));

        request.setAttribute("meals", mealWithExceeds);
    }
}