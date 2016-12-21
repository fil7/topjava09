<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://localhost:8080/topjava/functions" %>
<html>
<head>
    <title>Meal list</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>

<%--<jsp:forward page="/meals?action=listMealWithExceed" />--%>

<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<div class="col-md-6">
    <table class="table">
        <thead>
        <tr>
            <th>
                <c:out value="Date"/>
            </th>
            <th>
                <c:out value="Time"/>
            </th>
            <th>
                <c:out value="Description"/>
            </th>
            <th>
                <c:out value="Calories"/>
            </th>
            <th colspan=2>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal" varStatus="loop">
            <c:set var="isExceed" value="${meal.isExceed() ? 'red' : 'green'}" />
            <tr class="${isExceed}">
                <td>
                    <c:out value="${f:formatLocalDateTime(meal.getDateTime(), 'dd.MM.yyyy')}"/>
                </td>
                <td>
                    <c:out value="${f:formatLocalDateTime(meal.getDateTime(), 'HH:mm')}"/>
                </td>
                <td>
                    <c:out value="${meal.getDescription()}"/>
                </td>
                <td>
                    <c:out value="${meal.getCalories()}"/>
                </td>
                <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Update</a></td>
                <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
            </tr>

        </c:forEach>
        </tbody>
    </table>

    <p><a href="meals?action=insert">Add User</a></p>
</div>

</body>
</html>
