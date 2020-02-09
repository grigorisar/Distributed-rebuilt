<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="../../resources/navbar.jsp"%>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Staff Manager</title>


    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css" integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47" crossorigin="anonymous" >

    <!-- styling for the datatable -->
    <link rel="stylesheet" type="text/css" href=" https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css ">
    <link rel="stylesheet" type="text/css" href=" https://cdn.datatables.net/1.10.20/css/dataTables.jqueryui.min.css ">


    <!-- <%--  jquery sources  --%> -->
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- <%--  scripts sources for jquery data tables --%> -->
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


<table id="table" class="display"  align="center">
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
        <th>Position Title</th>
    </tr>
    </thead>
    <tbody id="table_body">

    <c:forEach var="tempStaff" items="${staff}">

        <tr>
            <td>${tempStaff.id}</td>
            <td>${tempStaff.firstName}</td>
            <td>${tempStaff.lastName}</td>
            <td>${tempStaff.username}</td>
            <td>${tempStaff.position}</td>
        </tr>

    </c:forEach>

    </tbody>
</table>

<br>


<div class="pure-g">

    <div align="center"    class="pure-u-1-3">

        <input type="button" name="addStaff" id="addStaff" value="Add Staff">
        <br>

        <form style="display:none" id="staff_creation"  name="staff_creation" method="post" action="${pageContext.request.contextPath}/manager/create_user_process">

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

            <div  class="form-group" id="PositionDiv">
                <label for="position">Position Title</label><br>
                <input required type="text" name="position" id="position" placeholder="Position Title" maxlength="45"><br>

            </div>

            <div class="form-group">
                <input required type="submit" class="button" value = "Submit" id="create">
            </div>

            <input hidden type="text" required name="role" id="role" value="Staff" ><br>

            <!-- for the jquery ajax post request -->
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </form>

    </div>
    <div align="center" class="pure-u-1-3">

        <input type="button" name="updateStaff" id="updateStaff" value="Update Selected Staff">

        <br>

        <div style="display:none" id="update">

            <form  id="staff_update"  name="staff_update" method="post" action="${pageContext.request.contextPath}/manager/update_user_process">
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
                    <label for="position">Department</label><br>
                    <input required type="text" name="position" id="position_u" placeholder="Position Title" maxlength="45"><br>

                </div>

                <div  class="form-group" >
                    <input hidden type="text" required name="old_username" id="old_username"  ><br>
                </div>

                <div  class="form-group" >
                    <input hidden type="text" required name="role" id="role_u" value="Staff" ><br>
                </div>

                <input required type="submit" class="button" value = "Update" id="updateB">


                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>

        </div>
    </div>
    <div align="center" class="pure-u-1-3" >

        <input type="button" name="deleteStaff" id="deleteStaff" value="Delete Selected Staff">

        <br>

        <div style="display:none" id="delete">

            <form  id="staff_delete"  name="staff_delete" method="post" action="${pageContext.request.contextPath}/manager/delete_user_process">
                <!-- style="display:none" -->
                <div class="form-group">
                    <label for="username">Username</label> <br>
                    <input required type="text" name="username" id="username_d" placeholder="Username" maxlength="50"><br>
                </div>

                <input type="submit" name="confirm" id="confirm" value="Confirm">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>

        </div>


    </div>
</div>
<br>
<div id="bottom" align="center"></div>

<script type="text/javascript">

    $('table tr').mouseover(function() {    //on hover color script
        $(this).addClass('hovered');
    }).mouseout(function() {
        $(this).removeClass('hovered');
    });

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


        $("#addStaff").click (function(e) {

            // console.log("you clicked me")
            // $("#staff_creation").toggle();
            $('#staff_creation').trigger("reset");    //reset form
            // $('#bottom1').empty();

            if (  $("#staff_creation").css('display') === 'none' ) {
                $("#staff_creation").show();
            } else {
                $("#staff_creation").hide();

            }


        });

        $("#updateStaff").click (function(e) {

            $('#staff_update').trigger("reset");
            // $('#bottom2').empty();
            if (  $("#update").css('display') == 'none' ) {
                $("#update").show();

                var username = $('.selected td').eq(3).text();

                if (username == ""){

                    // $("#table_body").empty();

                } else {

                    $("#old_username").val($('.selected td').eq(3).text());     //0 is ID
                    $("#username_u").val($('.selected td').eq(3).text());
                    $("#lastname_u").val($('.selected td').eq(2).text());
                    $("#firstname_u").val($('.selected td').eq(1).text());
                    $("#position_u").val($('.selected td').eq(4).text());

                }

            } else {
                $("#update").hide();
                $('#update').trigger("reset");    //reset form

            }
        });

        $("#deleteStaff").click (function(e) {               //on click function for delete staff service

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
                // $('#bottom3').empty();

            }


        });


        /* attach a submit handler to the form */       //for form staff_creation
        $("#staff_creation").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#staff_creation').serialize(),
                // dataType: "plain/text",
                success: function(data) {                                   //on success of ajax
                    //var obj = jQuery.parseJSON(data); if the dataType is not specified as json uncomment this
                    console.log("posting sucessful");
                    $("#bottom").empty().append(data)
                },
                error: function(xhr, request, error) {                                 //on error
                    //  = eval("(" + xhr.responseText + ")");       //eval is evil dont use it
                    // alert(err.Message);
                    let err = xhr.responseText
                    alert(err)
                    $('#bottom').empty().append("Error Encountered with request " + error)

                },
                complete: function () {                             //on completion
                    console.log("creation finished")
                }
            });

        });

        $("#staff_update").submit(function(event) {    //posting for user update
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            $.ajax({
                type: "POST",
                url: url,
                data : $('#staff_update').serialize(),
                // dataType: "plain/text",
                success: function(data) {
                    console.log("posting sucessful")
                    $("#bottom").empty().append(data)
                },
                error: function(xhr, request, error) {
                    var err = xhr.responseText
                    alert(err)
                    $('#bottom').empty().append("Error Encountered with request " + error)

                },
                complete: function () {                             //on completion
                    console.log("update finished")
                }
            });

        });

        $("#staff_delete").submit(function(event) {

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
                data : $('#staff_delete').serialize(),
                // dataType: "plain/text",
                success: function(data) {
                    console.log("posting sucessful")
                    $("#bottom").empty().append(data)
                },
                error: function(xhr, request, error) {
                    var err = xhr.responseText
                    alert(err)
                    $('#bottom').empty().append("Error Encountered with request " + error)

                },
                complete: function () {                             //on completion
                    console.log("deletion finished")
                }
            });


        });



    });
</script>


