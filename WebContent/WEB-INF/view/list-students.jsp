<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>
    <!-- reference our style sheet -->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2> UDM - User Data Manager</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <!--  add our html table here -->
        <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Department</th>
                <th>Year</th>
                <th>Failed Classes</th>
            </tr>
            <!-- loop over and print our customers -->
            <c:forEach var="tempStudent" items="${students}">

                <tr>
                    <td>${tempStudent.username}</td>
                    <td>${tempStudent.firstName}</td>
                    <td>${tempStudent.lastName}</td>
                    <td>${tempStudent.dept}</td>
                    <td>${tempStudent.year}</td>
                    <td>${tempStudent.failed}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
