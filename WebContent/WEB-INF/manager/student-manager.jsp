<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="../../resources/navbar.jsp"%>

</html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student Manager</title>


    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css" integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47" crossorigin="anonymous" >

    <!-- styling for the datatable -->
    <link rel="stylesheet" type="text/css" href=" https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css ">
    <link rel="stylesheet" type="text/css" href=" https://cdn.datatables.net/1.10.20/css/dataTables.jqueryui.min.css ">


    <!-- <%--  jquery sources  --%> -->
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- <%--  scripts sources for jquery data tables --%> -->
    <script type="text/javascript" src=" https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.jqueryui.min.js"></script>



    <style>

        td {border: 1px #DDD solid; padding: 5px; cursor: pointer;}

        .l-box {
            padding: 1em;
        }
        /*
              .hovered td {
                  background: #ddd;
              }
        */
    </style>

</head>
<body>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/buttons.css"/>
<br>
<table id="table" class="display"  align="center">
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
        <th>Department</th>
        <th>Current Year</th>
        <th># of Failed Classes</th>
    </tr>
    </thead>
    <tbody id="table_body">

    <c:forEach var="tempStudent" items="${students}">

        <tr>
            <td>${tempStudent.id}</td>
            <td>${tempStudent.firstName}</td>
            <td>${tempStudent.lastName}</td>
            <td>${tempStudent.username}</td>
            <td>${tempStudent.dept}</td>
            <td>${tempStudent.year}</td>
            <td>${tempStudent.failed}</td>
        </tr>

    </c:forEach>

    </tbody>
</table>

<br>


<div class="pure-g">

    <div align="center"    class="pure-u-1-3">

        <input type="button" name="addStudent" id="addStudent" value="Add Student">
        <br>

        <form style="display:none" id="user_creation"  name="user_creation" method="post" action="${pageContext.request.contextPath}/manager/create_user_process">

            <div class="form-group">
                <label for="username">Username</label> <br>
                <input required type="text" name="username" id="username" placeholder="Username" maxlength="50"><br>
            </div>

            <div class="form-group">
                <label for="password">Password</label><br>
                <input required type="password" name="password" id="password" placeholder="Password" maxlength="100"><br>

            </div>

            <div class="form-group">
                <label for="firstname">First Name</label><br>
                <input required type="text" name="firstname" id="firstname" placeholder="First Name" maxlength="45"><br>

            </div>

            <div class="form-group">
                <label for="lastname">Last Name</label><br>
                <input required type="text" name="lastname" id="lastname" placeholder="Last Name" maxlength="45"><br>

            </div>

            <div  class="form-group" id="YearField">
                <label for="year">Year</label><br>
                <input required step="1" type="text" pattern="\d+" name="year" id="year" placeholder="Year" maxlength="5"><br>

            </div>

            <div  class="form-group" id="Department">
                <label for="dept">Department</label><br>
                <input required type="text" name="dept" id="dept" placeholder="Department Name" maxlength="45"><br>

            </div>

            <div  class="form-group" id="FailedClasses">
                <label for="failed">Failed Classes</label><br>
                <input required step="1" type="number" pattern="\d+" name="failed" id="failed" placeholder="Failed Classes" maxlength="2"><br>

            </div>

            <input required type="submit" class="button" value = "Submit" id="create">

            <div  class="form-group" >
                <input hidden type="text" required name="role" id="role" value="Student" ><br>

            </div>
            <!-- for the jquery ajax post request -->
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </form>


    </div>
    <div align="center" class="pure-u-1-3">

        <input type="button" name="updateStudent" id="updateStudent" value="Update Selected Student">

        <br>

        <div style="display:none" id="update">

            <form  id="user_update"  name="user_creation" method="post" action="${pageContext.request.contextPath}/manager/update_user_process">
                <!-- style="display:none" -->
                <div class="form-group">
                    <label for="username">Username</label> <br>
                    <input required type="text" name="username" id="username_u" placeholder="Username" maxlength="50"><br>
                </div>

                <div class="form-group">
                    <label for="firstname">First Name</label><br>
                    <input required type="text" name="firstname" id="firstname_u" placeholder="First Name" maxlength="45"><br>

                </div>

                <div class="form-group">
                    <label for="lastname">Last Name</label><br>
                    <input required type="text" name="lastname" id="lastname_u" placeholder="Last Name" maxlength="45"><br>

                </div>

                <div  class="form-group" >
                    <label for="year">Year</label><br>
                    <input required step="1" type="number" pattern="\d+" name="year" id="year_u" placeholder="Year" maxlength="5"><br>

                </div>

                <div  class="form-group" >
                    <label for="dept">Department</label><br>
                    <input required type="text" name="dept" id="dept_u" placeholder="Department Name" maxlength="45"><br>

                </div>

                <div  class="form-group" >
                    <label for="failed">Failed Classes</label><br>
                    <input required step="1" type="number" pattern="\d+" name="failed" id="failed_u" placeholder="Failed Classes" maxlength="2"><br>

                </div>

                <div  class="form-group" >
                    <input hidden type="text" required name="old_username" id="old_username"  ><br>

                </div>

                <input required type="submit" class="button" value = "Update" id="updateB">

                <div  class="form-group" >
                    <input hidden type="text" required name="role" id="role_u" value="Student" ><br>

                </div>

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>

        </div>
    </div>
    <div align="center" class="pure-u-1-3" >

        <input type="button" name="deleteStudent" id="deleteStudent" value="Delete Selected Student">

        <br>

        <div style="display:none" id="delete">

            <form  id="user_delete"  name="user_delete" method="post" action="${pageContext.request.contextPath}/manager/delete_user_process">
                <!-- style="display:none" -->
                <div class="form-group">
                    <label for="username">Username</label> <br>
                    <input required type="text" name="username" id="username_d" placeholder="Username" maxlength="50"><br>
                </div>
                <div  class="form-group" >
                    <input hidden type="text" required name="role" id="role_d" value="Student" ><br>

                </div>

                <input type="submit" name="confirm" id="confirm" value="Confirm">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>

        </div>


    </div>
</div>

<div align="center" id="bottom"> </div>

<script type="text/javascript">

    var table = $('#table').DataTable();

    $('table tr').mouseover(function() {    //on hover color script
        $(this).addClass('hovered');
    }).mouseout(function() {
        $(this).removeClass('hovered');
    });

    function append_json(data) {
        var table = $('#table').DataTable();
        var t = document.getElementById('table');

        // table.rows().remove;
        table.clear();
        data.forEach(function (object) {

            table.row.add( [
                object.id,
                object.firstName,
                object.lastName,
                object.username,
                object.dept,
                object.year,
                object.failed
            ] ).draw(true);

        });
    };

    function refresh() {
        $("#table_body" ).empty();

        let u = ("${pageContext.request.contextPath}/manager/get_students_json");


        $.getJSON( u, function (data) {
            console.log(data);

            // var data = JSON.parse(this.responseText); // convert the response to a json object
            append_json(data);// pass the json object to the append_json function


        });

    }

    function highlight(e) {                           //highlight function
        if (selected[0]) {
            selected[0].className = ''
        }

        e.target.parentNode.className = 'selected';

    }

    var table = document.getElementById('table');

    var selected = table.getElementsByClassName('selected');

    table.onclick = highlight;


    $(document).ready(function() {

        var table = $('#table').DataTable();    //make "table" into a datatable using the library


        $("#addStudent").click (function(e) {

            // console.log("you clicked me")
            // $("#user_creation").toggle();
            $('#user_creation').trigger("reset");    //reset form
            // $('#bottom1').empty();

            if (  $("#user_creation").css('display') === 'none' ) {
                $("#user_creation").show();
            } else {
                $("#user_creation").hide();

            }


        });

        $("#updateStudent").click (function(e) {

            $('#user_update').trigger("reset");
            // $('#bottom').empty();
            if (  $("#update").css('display') == 'none' ) {
                $("#update").show();

                var username = $('.selected td').eq(3).text();

                if (username == ""){

                    // $("#table_body").empty();

                } else {

                    $("#old_username").val($('.selected td').eq(3).text());
                    $("#username_u").val($('.selected td').eq(3).text());
                    $("#lastname_u").val($('.selected td').eq(2).text());
                    $("#firstname_u").val($('.selected td').eq(1).text());
                    $("#dept_u").val($('.selected td').eq(4).text());
                    $("#year_u").val($('.selected td').eq(5).text());
                    $("#failed_u").val($('.selected td').eq(6).text());

                }

            } else {
                $("#update").hide();
                $('#update').trigger("reset");    //reset form

            }
        });

        $("#deleteStudent").click (function(e) {               //on click function for delete student service

            if (  $("#delete").css('display') == 'none' ) {
                $("#delete").show();

                var username = $('.selected td').eq(3).text();

                if (username == ""){

                    // $("#table_body").empty();

                } else {

                    $("#username_d").val($('.selected td').eq(3).text());

                }

            } else {
                $("#delete").hide();
                $('#delete').trigger("reset");    //reset form
                // $('#bottom').empty();

            }


        });


        /* attach a submit handler to the form */       //for form user_creation
        $("#user_creation").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#user_creation').serialize(),
                // dataType: "plain/text",
                success: function(data) {                                   //on success of ajax
                    //var obj = jQuery.parseJSON(data); if the dataType is not specified as json uncomment this
                    console.log("posting sucessful");
                    $("#bottom").empty().append(data);
                    // window.location.reload();
                },
                error: function(xhr, request, error) {                                 //on error
                    //  = eval("(" + xhr.responseText + ")");       //eval is evil dont use it
                    // alert(err.Message);
                    let err = xhr.responseText;
                    alert(err);
                    $('#bottom').empty().append("Error Encountered with request " + error);

                },
                complete: function () {                             //on completion
                    console.log("creation finished");
                    refresh();
                }
            });

        });

        $("#user_update").submit(function(event) {    //posting for user update
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            $.ajax({
                type: "POST",
                url: url,
                data : $('#user_update').serialize(),
                // dataType: "plain/text",
                success: function(data) {
                    console.log("posting sucessful");
                    $("#bottom").empty().append(data);
                    // window.location.reload();
                },
                error: function(xhr, request, error) {
                    var err = xhr.responseText;
                    alert(err);
                    $('#bottom').empty().append("Error Encountered with request " + error);
                },
                complete: function () {                             //on completion
                    console.log("update finished");
                    refresh();
                }
            });

        });

        $("#user_delete").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();
            console.log("deletion starts");

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#user_delete').serialize(),
                // dataType: "plain/text",
                success: function(data) {
                    console.log("posting sucessful");
                    $("#bottom").empty().append(data);
                    // window.location.reload();
                },
                error: function(xhr, request, error) {
                    var err = xhr.responseText;
                    alert(err);
                    $('#bottom').empty().append("Error Encountered with request " + error);
                },
                complete: function () {                             //on completion
                    console.log("deletion finished");
                    refresh();
                }
            });


        });



    });
</script>


</body>
</html>