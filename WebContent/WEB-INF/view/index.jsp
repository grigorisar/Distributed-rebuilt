<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Spring MVC demo project!</h1>

<a href="<c:url value="/showForm"></c:url>">Show Form</a>
<br/>
<a href="<c:url value="/student/list"></c:url>">Show Students</a>
</body>
</html>