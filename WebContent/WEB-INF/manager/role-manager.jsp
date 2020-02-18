<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="../../resources/navbar.jsp"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Role Manager</title>


    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css" integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47" crossorigin="anonymous" >

    <!-- styling for the datatable -->
    <link rel="stylesheet" type="text/css" href=" https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css ">
    <link rel="stylesheet" type="text/css" href=" https://cdn.datatables.net/1.10.20/css/dataTables.jqueryui.min.css ">
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttons.css"/>--%>
    <br>

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
        body.input{
            color: black !important;
        }
    </style>
</head>

<body>

<table id="table" class="display"  align="center">
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
    </tr>
    </thead>
    <tbody id="table_body">
    <c:forEach var="role" items="${roles}">
        <tr>
            <td style="text-align: center">${role.id}</td>
            <td style="text-align: center">${role.title}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<%--create role --%>
<div class="pure-g">
    <%--create role--%>
    <div align="center"    class="pure-u-1-3">
        <link type="text/css" rel="stylesheet"
              href="${pageContext.request.contextPath}/resources/css/buttons.css"/>

        <input type="button" name="addRole" id="addRole" value="New Role">
        <br>
        <form style=" display:none" id="role_creation"  name="role_creation" method="post" action="${pageContext.request.contextPath}/manager/create_role">
            <div class="form-group">
                <label for="title">Title</label> <br>
                <input required type="text" name="title" id="title"  placeholder="Title" maxlength="50"><br>
            </div>

            <input required type="submit" class="button" value = "Submit" id="create">

            <!-- for the jquery ajax post request -->
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>

    <%--update role--%>
    <br>

    <div align="center"    class="pure-u-1-3">
        <input type="button" name="updateRole" id="updateRole" value="Update Role">
        <br>
        <form style="display:none" id="role_update"  name="role_update" method="post" action="${pageContext.request.contextPath}/manager/update_role">
            <div class="form-group">
                <label for="title">Title</label> <br>
                <input required type="text" name="title" id="title_u"  placeholder="Title" maxlength="50"><br>
            </div>

            <div  class="form-group" >
                <input hidden type="text" required name="old_title" id="old_title"  ><br>
            </div>

            <input required type="submit" class="button" value = "Submit" id="update">

            <!-- for the jquery ajax post request -->
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>

    <%--delete role--%>
    <br>
    <div align="center"    class="pure-u-1-3">

        <input type="button" name="deleteRole" id="deleteRole" value="Delete Roles">
        <br>
        <form style="display:none" id="role_delete"  name="role_delete" method="post" action="${pageContext.request.contextPath}/manager/delete_role">
            <div class="form-group">
                <label for="title">Title</label> <br>
                <input required type="text" name="title" id="title_d"  placeholder="Title" maxlength="50"><br>
            </div>
            <input required type="submit" class="button" value = "Submit" id="delete">
            <!-- for the jquery ajax post request -->
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</div>

<div align="center" id="bottom"> </div>
</body>

<script type="text/javascript">

    // var table = $('#table').DataTable();

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
                object.title
            ] ).draw(true);

        });
    };

    function refresh() {
        $("#table_body" ).empty();

        let u = ("${pageContext.request.contextPath}/manager/role");


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


        $("#addRole").click (function(e) {

            // console.log("you clicked me")
            // $("#role_creation").toggle();
            $('#role_creation').trigger("reset");    //reset form
            // $('#bottom1').empty();

            if (  $("#role_creation").css('display') === 'none' ) {
                $("#role_creation").show();
            } else {
                $("#role_creation").hide();

            }


        });

        $("#updateRole").click (function(e) {

            $('#role_update').trigger("reset");
            // $('#bottom').empty();
            if (  $("#role_update").css('display') == 'none' ) {
                $("#role_update").show();

                var title = $('.selected td').eq(1).text();

                if (title == ""){

                    // $("#table_body").empty();

                } else {
                    $("#title_u").val($('.selected td').eq(1).text());
                    $("#old_title").val($('.selected td').eq(1).text());
                }

            } else {
                $("#tole_update").hide();
                $('#role_update').trigger("reset");    //reset form

            }
        });

        $("#deleteRole").click (function(e) {               //on click function for delete Role service

            if (  $("#role_delete").css('display') == 'none' ) {
                $("#role_delete").show();

                var title = $('.selected td').eq(1).text();

                if (title == ""){

                    // $("#table_body").empty();

                } else {

                    $("#title_d").val($('.selected td').eq(1).text());

                }

            } else {
                $("#role_delete").hide();
                $("#role_delete").trigger("reset");    //reset form
                // $('#bottom').empty();

            }


        });


        /* attach a submit handler to the form */       //for form role_creation
        $("#role_creation").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#role_creation').serialize(),
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

        $("#role_update").submit(function(event) {    //posting for user update
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            $.ajax({
                type: "POST",
                url: url,
                data : $('#role_update').serialize(),
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

        $("#role_delete").submit(function(event) {

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
                data : $('#role_delete').serialize(),
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


<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Role Manager</title>


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

</html>