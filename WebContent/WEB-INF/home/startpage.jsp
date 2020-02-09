<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../../resources/navbar.jsp"%>

<title>Still Testing</title>
<div>

</div>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttons.css"/>
<h2 style="align-content: center">Hello!</h2>
<div class="main">
<%--    <h3><a href="<c:url value="/showForm"></c:url>">Show Form</a></h3>--%>
    <h3><a href="<c:url value="/student/"></c:url>">Show Student Menu</a></h3>
    <h3><a href="<c:url value="/staff/"></c:url>">Show Staff Menu</a></h3>
    <h3><a href="<c:url value="/manager/"></c:url>">Show Manager Menu</a></h3>
    <h3><a href="<c:url value="/logout"></c:url>">Sign Out</a></h3>
</div>
