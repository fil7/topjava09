<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://localhost:8080/topjava/functions" %>
<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>

<%--http://localhost:8080/topjava/meals?action=edit&id=5--%>

<form method="post" id="editMeal">
    <div class="form-group">
        <label> DateTime
            <input type="datetime-local" id="datetime" name="datetime" class="form-control"
                   value="${meal.getDateTime()}">
        </label>
    </div>
    <div class="form-group">
        <label> Description
            <input type="text" id="description" name="description" class="form-control"
                value="${meal.getDescription()}">
        </label>
    </div>
    <div class="form-group">
        <label> Calories
            <input type="number" id="calories" name="calories" class="form-control"
            value="${meal.getCalories()}" >
        </label>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>

</body>
</html>
