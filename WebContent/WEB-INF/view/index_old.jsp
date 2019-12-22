<%--
  gr.hua.distributed.team52.Actors.User: owl
  Date: 14/11/2019
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page session="true" %>
<html>


<head>
  <title>Live From Mars</title>



</head>

<body>



<br> <br> <br>

<button onclick="location.href = ' http://localhost:8080/studentform.jsp';" id="studentButton" class="float-left submit-button" >Student</button>

<button onclick="location.href = 'http://localhost:8080/kSystems_war_exploded/staffform.jsp';" id="staffButton" class="float-left submit-button" >Staff</button>

<br>
<form name="petitions" method="post" action="StudentServlet">
  <select name="list" id="list"  size="10" >
    <option value="choice1">choice1</option>
    <option value="choice2">choice2</option>
    <option value="choice3">choice3</option>
    <option value="choice4">choice4</option>
    <option value="choice5">choice5</option>
    <option value="choice6">choice6</option>
  </select>
  <br>
  <input type="submit" value="Accept" />
</form>






<select name="database1" size="12">

  <c:forEach items="${sessionScope.list}" var="databaseValue">
    <option value="${databaseValue}">${databaseValue}</option>
  </c:forEach>
</select>


<div align = "center">
  <p>${sessionScope.list}</p>
</div>


</body>
</html>
