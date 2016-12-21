package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.actions.Action;
import ru.javawebinar.topjava.web.functions.Dates;
import ru.javawebinar.topjava.web.requests.MealRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Dao dao;

    public MealServlet() {
        super();
        dao = new MealDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        doRequest(action, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer mealId = null;
        if (!isEmptyParam("id", request)) {
            mealId = Integer.parseInt(request.getParameter("id"));
        }

        LocalDateTime localDateTime = null;
        if (!isEmptyParam("datetime", request)) {
            localDateTime = Dates.toLocalDateTime(
                    request.getParameter("datetime").replace('T',' ').replace('-','/'),
                    "yyyy/MM/dd HH:mm");
        }

        String description = request.getParameter("description");

        int calories = 0;
        if (!isEmptyParam("calories", request)) {
            calories = Integer.parseInt(request.getParameter("calories"));
        }

        if (mealId == null) {
            dao.create(new Meal(localDateTime, description, calories));
        } else {
            dao.update(new Meal(mealId, localDateTime, description, calories));
        }

        doRequest("listMealWithExceed", request, response);
    }

    private boolean isEmptyParam(String nameParam, HttpServletRequest request) {
        String param = request.getParameter(nameParam);
        return param == null || "".equals(param);
    }

    private void doRequest(String requestType, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward = "";
        Action<Meal, Integer> dbMealAction = MealRequest.getActionByType(requestType);
        if (dbMealAction != null) {
            dbMealAction.execute(dao, request, response);
            forward = MealRequest.getForwardPage(requestType);
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }
}
