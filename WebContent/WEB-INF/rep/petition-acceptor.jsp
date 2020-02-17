<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>


</html>

<head>
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
        <th>BIO</th>
    </tr>
    </thead>
    <tbody id="table_body">

    <c:forEach var="tempPet" items="${petitions}">
        <tr>
            <td style="text-align: center">${tempPet.id}</td>
            <td style="text-align: center">${tempPet.title}</td>
            <td style="text-align: center">${tempPet.description}</td>
            <td style="text-align: center">
<%--                create link to new page for student with current username--%>
                <a href="${pageContext.request.contextPath}/rep/get_bio?username=${tempPet.student_username}" target="_blank"  >BIO</a>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>


<div align="center">
    <%--create role--%>

        <input type="button" name="getPetition" id="getPetition" value="Get Selected Petition">
        <br>
        <form style="display:none" id="petition_accept"  name="petition_accept" method="post" action="${pageContext.request.contextPath}/rep/accept_petition_process">

            <div class="form-group">
                <label for="title">Title</label> <br>
                <input required  type="text" name="title" id="title"  placeholder="Title" maxlength="50"><br>
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


        $("#getPetition").click (function(e) {

            // console.log("you clicked me")
            // $("#role_creation").toggle();
            $('#role_creation').trigger("reset");    //reset form
            $('#bottom1').empty();

            if (  $("#petition_accept").css('display') === 'none' ) {
                $("#petition_accept").show();

                var title = $('.selected td').eq(1).text();

                if (title == ""){
                } else {

                    $("#title").val($('.selected td').eq(1).text());

                }
            } else {
                $("#petition_accept").hide();
            }
        });


        /* attach a submit handler to the form */       //for form role_creation
        $("#petition_accept").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');
            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $('#petition_accept').serialize(),
                // dataType: "plain/text",
                success: function(data) {                                   //on success of ajax
                    //var obj = jQuery.parseJSON(data); if the dataType is not specified as json uncomment this
                    console.log("posting sucessful");
                    $("#bottom1").empty().append(data);
                    // window.location.reload();
                },
                error: function(xhr, request, error) {                                 //on error
                    //  = eval("(" + xhr.responseText + ")");       //eval is evil dont use it
                    // alert(err.Message);
                    let err = xhr.responseText;
                    alert(err);
                    $('#bottom1').empty().append("Error Encountered with request " + error)
                },
                complete: function () {                             //on completion

                    console.log("submission finished");
                }


            });

            event.preventDefault();


        });

    });
</script>


</body>
</html>

