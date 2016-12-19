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
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal" varStatus="loop">
            <c:choose>
                <c:when test="${meal.isExceed()}">
                    <c:set var="isExceed" value="red" />
                </c:when>
                <c:otherwise>
                    <c:set var="isExceed" value="green" />
                </c:otherwise>
            </c:choose>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
