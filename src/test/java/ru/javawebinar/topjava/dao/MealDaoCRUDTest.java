package ru.javawebinar.topjava.dao;

import org.junit.runners.Parameterized;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.LinkedList;


public class MealDaoCRUDTest extends AbstractCRUDTest<Meal> {

    private Dao service;

    /**
     * Creates class with specified test object. This object will try to pass
     * CRUD-test.
     *
     * @param testObject object to test
     */
    public MealDaoCRUDTest(Meal testObject) {
        super(testObject);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        Collection<Object[]> data = new LinkedList<Object[]>();
        for (Meal meal : MealsUtil.getDefaultMeals()) {
            data.add(new Object[]{meal});
        }
        return data;
    }

    @Override
    public void delete(Meal testObject) {
        service.delete(testObject.getId());
    }

    @Override
    public Meal select(Meal testObject) {
        return (Meal)service.read(testObject.getId());
    }

    @Override
    public void update(Meal testObject) {
        service.update(testObject);
    }

    @Override
    public void insert(Meal testObject) {
        service.create(testObject);
    }

    @Override
    public void prepareDatabaseConnection() {
        service = new MealDao();
    }
}