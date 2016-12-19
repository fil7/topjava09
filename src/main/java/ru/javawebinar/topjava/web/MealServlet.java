package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Meal> meals = getDefaultMeals();
        List<MealWithExceed> mealWithExceeds = getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, DEFAULT_CALORIES_PER_DAY);
        request.setAttribute("meals", mealWithExceeds);
        getServletContext().getRequestDispatcher("/mealList.jsp").forward(request, response);
        // response.sendRedirect("mealList.jsp");
    }
}
