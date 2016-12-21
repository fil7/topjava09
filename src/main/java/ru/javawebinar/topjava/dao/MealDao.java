package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.TimeUtil.convertToDate;
import static ru.javawebinar.topjava.util.TimeUtil.convertToLocalDateTime;

/**
 * Dao stands for Data Access Object.
 * It contains the logic for  database operation.
 */
public class MealDao implements Dao<Meal, Integer> {

    private Connection connection;
    Logger logger = LoggerFactory.getLogger(MealDao.class);


    public MealDao() {
        connection = DbUtil.getConnection();
    }

    public enum MealsTable {
        ID("id"),
        DATETIME("dateTime"),
        DESCRIPTION("description"),
        CALORIES("calories")
        ;

        private final String columnName;

        MealsTable(String columnName) {
            this.columnName = columnName;
        }

        @Override
        public String toString() {
            return  columnName;
        }
    }

    @Override
    public void create(Meal meal) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO meals(dateTime,description,calories) VALUES (?,?,?)");
            // Parameters start with 1
            statement.setDate(1,
                    new java.sql.Date(convertToDate(meal.getDateTime()).getTime()));
            statement.setString(2, meal.getDescription());
            statement.setInt(3, meal.getCalories());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Failed to add new Meal: " + meal.getId());
        }
    }

    @Override
    public void delete(Integer mealId) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM meals WHERE id=?");
            statement.setInt(1, mealId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Failed to delete Meal: " + mealId);
        }
    }

    @Override
    public void update(Meal meal) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("UPDATE meals SET dateTime=?, description=?, calories=? " +
                            "WHERE id=?");
            // Parameters start with 1
            statement.setDate(1,
                    new java.sql.Date(convertToDate(meal.getDateTime()).getTime())
            );
            statement.setString(2, meal.getDescription());
            statement.setInt(3, meal.getCalories());
            statement.setInt(4, meal.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Failed to update Meal: " + meal.getId());
        }
    }

    @Override
    public List<Meal> getList() {
        List<Meal> meals = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM meals");
            while (result.next()) {
                Meal meal = getMealFromResultSet(result);
                meals.add(meal);
            }
        } catch (SQLException e) {
            logger.warn("Failed to get list of Meals");
        }
        return meals;
    }

    @Override
    public Meal read(Integer mealId) {
        Meal meal = null;
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM meals where id=?");
            statement.setInt(1, mealId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                meal = getMealFromResultSet(result);
            }

        } catch (SQLException e) {
            logger.warn("Failed to get meal by id: " + mealId);
        }
        return meal;
    }

    private Meal getMealFromResultSet(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        java.util.Date date = result.getTimestamp("dateTime");
        String description = result.getString("description");
        int calories = result.getInt("calories");
        Meal meal = new Meal(id, convertToLocalDateTime(date), description, calories);
        return meal;
    }
}
