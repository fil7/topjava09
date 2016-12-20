package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.actions.Action;
import ru.javawebinar.topjava.web.requests.MealRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static ru.javawebinar.topjava.util.MealsUtil.convertDate;

public class MealServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Dao dao;

    public MealServlet() {
        super();
        dao = new MealDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        Action<Meal, Integer> dbMealAction = MealRequest.getActionByType(action);
        if (dbMealAction != null) {
            dbMealAction.execute(dao, request, response);
            forward = MealRequest.getForwardPage(action);
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime localDateTime = null;
        try {
            Date datetime = new SimpleDateFormat("MM/dd/yyyy")
                    .parse(request.getParameter("datetime"));
            localDateTime = convertDate(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // TODO: need builder

        Integer mealId = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (mealId == null) {
            dao.create(new Meal(localDateTime, description, calories));
        } else {
            dao.update(new Meal(mealId, localDateTime, description, calories));
        }
        request.setAttribute("meals", dao.getList());
        request.getRequestDispatcher(Pages.LIST_MEAL).forward(request, response);
    }
}
