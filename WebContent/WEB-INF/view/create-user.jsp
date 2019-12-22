<%--
  Created by IntelliJ IDEA.
  User: owl
  Date: 21/12/2019
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<%--    load the jquery library BEFORE running the actual script--%>

    <title>The Original Jupiter Experience</title>



</head>
<body>
<form id="user_details" name="user_creation" method="post" action="/Springmvc1_war_exploded/user/CreateFormProccess">

    <div class="form-group">
        <label for="username">Username</label>>
        <input type="text" name="username" id="username" placeholder="Username" ><br>
    </div>
    <br>

    <div class="form-group">
        <label for="password">Password</label>>
        <input type="password" name="password" id="password" placeholder="Password" ><br>

    </div>
    <br>

    <div class="form-group">
        <label for="firstname">First Name</label>>
        <input type="text" name="firstname" id="firstname" placeholder="First Name" ><br>

    </div>
    <br>

    <div class="form-group">
        <label for="lastname">Last Name</label>>
        <input type="text" name="lastname" id="lastname" placeholder="Last Name"><br>

    </div>
    <br>

    <div class="form-group" id="YearField">
        <label for="year">Year</label>>
        <input type="text" name="year" id="year" placeholder="Year"><br>

    </div>
    <br>

    <div class="form-group" id="Department">
        <label for="dept">Department</label>>
        <input type="text" name="dept" id="dept" placeholder="Department Name"><br>

    </div>
    <br>

    <div class="form-group" id="FailedClasses">
        <label for="failed">Failed Classes</label>>
        <input type="text" name="failed" id="failed" placeholder="Failed Classes"><br>

    </div>
    <br>

    <div class="form-group">
        <input type="submit" value = "Submit">

    </div>

    <br>
    <div class="form-group">
        <select name="select" form="user_details" id="select">
            <option value="Student">Student</option>
            <option value="Staff">Staff</option>

        </select>
    </div>


</form>

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>

<script > //script to show the extra fields only when the student is selected

window.onload = function() {
    console.log("Called")
    $("#select").change(function() {
        console.log("Called1")
        if ($(this).val() == "Student") {
            $('#YearField').show();                             //for field year
            console.log("Called2")
            // $('#year').attr('required', '');
            // $('#year').attr('data-error', 'This field is required.');

            $('#Department').show();                             //for field Department
            // $('#dept').attr('required', '');
            // $('#dept').attr('data-error', 'This field is required.');

            $('#FailedClasses').show();                             //for field Year
            // $('#failed').attr('required', '');
            // $('#failed').attr('data-error', 'This field is required.');
        } else {
            $('#YearField').hide();
            // $('#year').removeAttr('required');
            // $('#year').removeAttr('data-error');

            $('#Department').hide();
            // $('#dept').removeAttr('required');
            // $('#dept').removeAttr('data-error');

            $('#FailedClasses').hide();
            // $('#failed').removeAttr('required');
            // $('#failed').removeAttr('data-error');
        }
    });
    $("#seeAnotherField").trigger("change");
};
</script>






</body>
</html>
