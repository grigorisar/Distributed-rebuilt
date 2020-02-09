<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24-Dec-19
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../../resources/navbar.jsp"%>

<title>Student Options</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css" />
<h2>Student Options</h2>
<div class="main">
    <h3><a href="<c:url value="/student/new_petition"></c:url>">New petition</a></h3>
<%--    <h3><a href="<c:url value="/student/list"></c:url>">Student List</a></h3>--%>
</div>

<script type="text/javascript">
    function redirect(link) {
        window.location.href =link;
    }

</script>

