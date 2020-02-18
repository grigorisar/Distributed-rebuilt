<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="../../resources/navbar.jsp"%>

</html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Service Manager</title>


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

<br>


<table id="table" class="display"  align="center">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Roles</th>
    </tr>
    </thead>
    <tbody id="table_body">

    <c:forEach var="tempService" items="${services}">

        <tr>
            <td>${tempService.id}</td>
            <td>${tempService.title}</td>
            <td>${tempService.description}</td>
            <td>
                <c:forEach var="tempRole" items="${tempService.roles}">
                    ${tempRole.title} ,
                </c:forEach>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<br>

<%--BUTTONS--%>
<div class="pure-g">
    <%--create service--%>
    <div align="center"    class="pure-u-1-3">

        <input type="button" name="addService" id="addService" value="Add Service">
        <br>
        <form style="display:none" id="service_creation"  name="service_creation" method="post" action="${pageContext.request.contextPath}/manager/create_service">

                <div class="form-group">
                    <label for="title">Title</label> <br>
                    <input required type="text" name="title" id="title" placeholder="Title" maxlength="50"><br>
                </div>
                <div class="form-group">
                    <label for="description">Description</label><br>
                    <input required type="description" name="description" id="description" placeholder="Description" maxlength="100"><br>
                </div>
                <div class="form-group">
                    <label for="roles">Roles : </label>
                    <div id="roles">
                        <c:forEach var="tempRole" items="${roles}">
                            <input class="checkbox" type="checkbox" name="role[]" value="${tempRole.title}">${tempRole.title}<br>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group">
                    <input required type="submit" class="button" value = "Submit" id="create">
                </div>

                <!-- for the jquery ajax post request -->
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        <div id="bottom1"></div>
    </div>

        <%--update service--%>
    <div align="center" class="pure-u-1-3">

        <input type="button" name="updateService" id="updateService" value="Update Selected Service">
        <br>
        <div style="display:none" id="update">
            <form  id="service_update"  name="service_creation" method="post" action="${pageContext.request.contextPath}/manager/update_service">
                <!-- style="display:none" -->
                <div class="form-group">
                    <label for="title">Title</label> <br>
                    <input required type="text" name="title" id="title_u" placeholder="Title" maxlength="50"><br>
                </div>

                <div class="form-group">
                    <label for="description">Description</label><br>
                    <input required type="text" name="description" id="description_u" placeholder="Description" maxlength="45"><br>
                </div>

                <div class="form-group">
                    <label for="roles_u">Roles</label><br>
                    <div id="roles_u">
                        <c:forEach var="tempRole" items="${roles}">
                            <input type="checkbox" name="role[]" value="${tempRole.title}">${tempRole.title}<br>
                        </c:forEach>
                    </div>
                </div>

                <div  class="form-group" >
                    <input hidden type="text" required name="old_title" id="old_title"><br>
                </div>

                <div class="form-group">
                    <input required type="submit" class="button" value = "Update" id="updateB">

                </div>

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>

        </div>
        <div id="bottom2"></div>
    </div>

        <%--delete service--%>
    <div align="center" class="pure-u-1-3" >

        <input type="button" name="deleteService" id="deleteService" value="Delete Selected Service">

        <br>

        <div style="display:none" id="delete">

            <form  id="service_delete"  name="service_delete" method="post" action="${pageContext.request.contextPath}/manager/delete_service">
                <!-- style="display:none" -->
                <div class="form-group">
                    <label for="title">Title</label> <br>
                    <input required type="text" name="title" id="title_d" placeholder="Title" maxlength="50"><br>
                </div>

                <input type="submit" name="confirm" id="confirm" value="Confirm">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>

            </form>

        </div>


    </div>

    <div id="bottom3"></div>
</div>

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


        $("#addService").click (function(e) {

            // console.log("you clicked me")
            // $("#service_creation").toggle();
            $('#service_creation').trigger("reset");    //reset form
            $('#bottom1').empty();

            if (  $("#service_creation").css('display') === 'none' ) {
                $("#service_creation").show();
            } else {
                $("#service_creation").hide();
            }


        });

        $("#updateService").click (function(e) {

            $('#role_update').trigger("reset");
            $('#bottom2').empty();
            if (  $("#update").css('display') == 'none' ) {
                $("#update").show();

                var title = $('.selected td').eq(1).text();

                if (title == ""){

                    // $("#table_body").empty();

                } else {

                    $("#old_title").val($('.selected td').eq(1).text());
                    $("#title_u").val($('.selected td').eq(1).text());
                    $("#description_u").val($('.selected td').eq(2).text());
                }

            } else {
                $("#update").hide();
                $('#update').trigger("reset");    //reset form

            }
        });

        $("#deleteService").click (function(e) {               //on click function for delete Service service

            if (  $("#delete").css('display') == 'none' ) {
                $("#delete").show();

                var title = $('.selected td').eq(1).text();

                if (title == ""){

                    // $("#table_body").empty();

                } else {

                    $("#title_d").val($('.selected td').eq(1).text());

                }

            } else {
                $("#delete").hide();
                $('#delete').trigger("reset");    //reset form
                $('#bottom3').empty();

            }


        });


        /* attach a submit handler to the form */       //for form service_creation
        $("#service_creation").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');
            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#service_creation').serialize(),
                // dataType: "plain/text",
                success: function(data) {                                   //on success of ajax
                    //var obj = jQuery.parseJSON(data); if the dataType is not specified as json uncomment this
                    console.log("posting sucessful");
                    $("#bottom1").empty().append(data);
                    window.location.reload();
                },
                error: function(xhr, request, error) {                                 //on error
                    //  = eval("(" + xhr.responseText + ")");       //eval is evil dont use it
                    // alert(err.Message);
                    let err = xhr.responseText;
                    alert(err);
                    $('#bottom1').empty().append("Error Encountered with request " + error);
                    window.location.reload();

                },
                complete: function () {                             //on completion

                    console.log("creation finished");
                }
            });

        });

        $("#service_update").submit(function(event) {    //posting for user update
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            $.ajax({
                type: "POST",
                url: url,
                data : $('#service_update').serialize(),
                // dataType: "plain/text",
                success: function(data) {
                    console.log("posting sucessful");
                    $("#bottom2").empty().append(data)
                    window.location.reload();
                },
                error: function(xhr, request, error) {
                    var err = xhr.responseText;
                    alert(err);
                    $('#bottom2').empty().append("Error Encountered with request " + error)
                    window.location.reload();

                },
                complete: function () {                             //on completion
                    console.log("update finished")
                }
            });

        });

        $("#service_delete").submit(function(event) {

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
                data : $('#service_delete').serialize(),
                success: function(data) {
                    console.log("posting sucessful");
                    $("#bottom3").empty().append(data)
                    window.location.reload();
                },
                error: function(xhr, request, error) {
                    var err = xhr.responseText;
                    alert(err);
                    $('#bottom3').empty().append("Error Encountered with request " + error)
                    window.location.reload();
                },
                complete: function () {                             //on completion
                    console.log("deletion finished")
                }
            });
        });
    });
</script>


</body>
</html>