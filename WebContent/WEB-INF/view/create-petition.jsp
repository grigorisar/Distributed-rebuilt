
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Pure css framework -->
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css" integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47" crossorigin="anonymous">

    <%--  jquery   --%>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>



    <title>Petition Creation</title>
</head>
<body>
    <div align="center">
        <br>
        <div id="top" name="top"></div>
        <br>
        <form class="pure-form-stacked" id="petition_details" name="petition_creation" method="post" action="/Springmvc1_war_exploded/student/create_petition_process">

            <div class="form-group">
                <label for="title">Title</label> <br>
                <input required type="text" name="title" id="title" placeholder="Title" maxlength="50"><br>
            </div>
            <br>

            <div class="form-group">
                <label for="description">Description</label><br>
                <input required type="text" name="description" id="description" placeholder="Description" maxlength="200"><br>

            </div>

            <div class="form-group">
                <input type="submit" class="pure button" value = "Submit" id="somebutton">

            </div>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </form>
    </div>

    <script>
        $("#petition_details").submit(function(event) {

            /* stop form from submitting normally */
            event.preventDefault();

            /* get some values from elements on the page: */
            var $form = $(this),
                url = $form.attr('action');

            console.log("posting happens")

            // var datastring = $("#user_details").serialize();
            $.ajax({
                type: "POST",
                url: url,
                data : $('#petition_details').serialize(),
                // dataType: "plain/text",
                success: function(data) {                                   //on success of ajax
                    //var obj = jQuery.parseJSON(data); if the dataType is not specified as json uncomment this
                    $("#top").empty().append(data)
                },
                error: function(xhr, request, error) {                                 //on error
                    //  = eval("(" + xhr.responseText + ")");       //eval is evil dont use it
                    // alert(err.Message);
                    var err = xhr.responseText
                    $('#top').empty().append("Error Encountered with request :" + err)

                },
                complete: function () {                             //on completion
                    console.log("posting finishes")
                }
            });
            /* Send the data using post */
            // var posting = $.post(url);


        });
    </script>
</body>
</html>
