package ru.javawebinar.topjava.web.requests;


import ru.javawebinar.topjava.web.Pages;
import ru.javawebinar.topjava.web.actions.Action;
import ru.javawebinar.topjava.web.actions.EmptyAction;
import ru.javawebinar.topjava.web.actions.mealActions.DeleteMealAction;
import ru.javawebinar.topjava.web.actions.mealActions.EditMealAction;
import ru.javawebinar.topjava.web.actions.mealActions.GetMealListWithExceedAction;

public enum MealRequest {

    DELETE("delete", new DeleteMealAction(), Pages.LIST_MEAL),
    EDIT("edit", new EditMealAction(), Pages.INSERT_OR_EDIT),
//    LIST_MEAL("listMeal", new GetMealListAction(), Pages.LIST_MEAL),
    LIST_MEAL_WITH_EXCEED("listMealWithExceed", new GetMealListWithExceedAction(), Pages.LIST_MEAL),
    EMPTY("", new EmptyAction(), Pages.INSERT_OR_EDIT);

    private final String type;
    private final Action action;
    private final String forwardPage;

    MealRequest(String type, Action action, String forwardPage) {
        this.type = type;
        this.action = action;
        this.forwardPage = forwardPage;
    }

    public static Action getActionByType(String type) {
            MealRequest request = MealRequest.fromString(type);
            return request.getAction();
    }

    public static String getForwardPage(String type) {
        MealRequest request = MealRequest.fromString(type);
        return request.getForwardPage();
    }

    public static MealRequest fromString(String text) {
        if (text != null) {
            for (MealRequest r : MealRequest.values()) {
                if (text.equalsIgnoreCase(r.type)) {
                    return r;
                }
            }
        }
        return EMPTY;
    }

    public Action getAction() {
        return action;
    }

    public String getType() {
        return type;
    }

    public String getForwardPage() {
        return forwardPage;
    }
}
