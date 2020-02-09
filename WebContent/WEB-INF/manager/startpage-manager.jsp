<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 23-Dec-19
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../resources/navbar.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/buttons.css"/>
<h1>Select a Service</h1>
<%-- NAVIGATE TO PAGE--%>
<h2><a href="<c:url value="/manager/role"></c:url>">Role Manager</a></h2>
<h2><a href="<c:url value="/manager/user"></c:url>">User Manager</a></h2>
<h2><a href="<c:url value="/manager/student"></c:url>">Student Manager</a></h2>
<h2><a href="<c:url value="/manager/staff"></c:url>">Staff Manager</a></h2>
<h2><a href="<c:url value="/manager/service"></c:url>">Service Manager & Role permissions</a></h2>
</body>
</html>