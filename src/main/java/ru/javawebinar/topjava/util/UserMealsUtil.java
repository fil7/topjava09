package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceeds = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        userMealWithExceeds.forEach(meal -> System.out.println(meal));
//        .toLocalDate();
//        .toLocalDate();
//        .toLocalTime();
    }

    /**
     * UserMealsUtil.getFilteredWithExceeded:
     * -  должны возвращаться только записи между startTime и endTime
     * -  поле UserMealWithExceed.exceed должно показывать,
     *    превышает ли сумма калорий за весь день параметра метода caloriesPerDay
     * Т.е UserMealWithExceed - это запись одной еды, но поле exceeded будет одинаково для всех записей за этот день.
     *
     */
    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumCaloriesByDays = mealList.stream()
                .collect(
                        Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(),
                                Collectors.summingInt(UserMeal::getCalories)));


        return mealList.stream()
                .filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                .map(u -> {
                            boolean isCaloriesAmountExceeded = sumCaloriesByDays.get(u.getDateTime().toLocalDate()) > caloriesPerDay;
                            return new UserMealWithExceed(u.getDateTime(), u.getDescription(), u.getCalories(), isCaloriesAmountExceeded);
                        }
                )
                .collect(Collectors.toList());

    }

}
