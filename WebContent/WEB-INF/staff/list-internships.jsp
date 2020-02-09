<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 27-Dec-19
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="../../resources/navbar.jsp"%>


<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>

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
            padding: 4em;
        }
    </style>

</head>
<body>
<%--<link type="text/css" rel="stylesheet"--%>
<%--      href="${pageContext.request.contextPath}/resources/css/buttons.css"/>  plz no--%>
<div id="wrapper">
    <div id="header">
        <h1>Internship Data Manager</h1>
    </div>
</div>

<div id="container">

    <div class="pure-g">

        <div align="left" class="pure-u-1-2">
            <div class="l-box" id="pending">
                <!--  add our html table here -->
                <table id="p_table">
                    <caption>Pending Internships</caption>
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Salary</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <!-- loop over and print interships -->
                    <tbody
                    <c:forEach var="tempInternshipP" items="${internshipsPending}">
                        <tr>
                            <td>${tempInternshipP.title}</td>
                            <td>${tempInternshipP.description}</td>
                            <td>${tempInternshipP.salary}</td>
                            <td>${tempInternshipP.status}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <%--            <div align="center" class="pure-u-1-3"> </div>--%>

        <div align="right" class="pure-u-1-2">
            <div class="l-box id="accepted">
            <!--  add our html table here -->
            <table id="a_table">
                <caption>Accepted Internships</caption>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Salary</th>
                    <th>Status</th>
                </tr>
                </thead>
                <!-- loop over and print interships -->
                <tbody

                <c:forEach var="tempInternshipA" items="${internshipsAccepted}">

                    <tr>
                        <td>${tempInternshipA.title}</td>
                        <td>${tempInternshipA.description}</td>
                        <td>${tempInternshipA.salary}</td>
                        <td>${tempInternshipA.status}</td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

<br>

<div align="center" class="main">


    <form align="center" class="pure-form-stacked" id="internship" name="internship" method="post" action="${pageContext.request.contextPath}/staff/accept_internship_process">

        <div class="form-group">
            <label for="title">Title</label>
            <input style="align-self: center" required type="text" name="title" id="title" placeholder="Title" maxlength="50">
        </div>

        <div class="form-group">
            <input type="button" class="pure button" value = "Get Selected" id="get_title">

        </div>


        <div class="form-group">
            <input type="submit" class="pure button" value = "Accept Internship" id="somebutton">

        </div>

        <%--            for csrf protection --%>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form>

    <br>

</div>

<div id="bottom" align="center" > </div>
</div>
<script type="text/javascript">
    /*
            function highlight1(e) {                           //highlight function
                if (selected[0]) {
                    selected[0].className = ''
                }

                e.target.parentNode.className = 'selected';

            }*/

    function highlight2(e) {                           //highlight function
        if (selected[0]) {
            selected[0].className = ''
        }

        e.target.parentNode.className = 'selected';

    }

    // var table1 = document.getElementById('accepted');
    var table2 = document.getElementById('pending');

    // var selected1 = table1.getElementsByClassName('selected');
    var selected = table2.getElementsByClassName('selected');

    // table1.onclick = highlight1;
    table2.onclick = highlight2;


    $(document).ready(function() {
        //create datatables
        var table = $('#a_table').DataTable();
        var table = $('#p_table').DataTable();


        /* attach a submit handler to the form */       //for form user_creation
        $("#internship").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#internship').serialize(),
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
                    console.log("posting finished")
                }
            });

        });

        $("#get_title").click (function(e) {

            console.log("clicked")

            var title = $('.selected td').eq(0).text();

            if (title === "") {

                // $("#table_body").empty();
                console.log("select is null ")

            } else {

                let l = $("#title").val($('.selected td').eq(0).text());
                console.log(l);
                $("#title").val($('.selected td').eq(0).text());

            }

        });

    })
</script>
</body>
</html>
