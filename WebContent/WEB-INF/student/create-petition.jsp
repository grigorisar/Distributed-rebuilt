
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header-footer.css">
<%@ include file="../../resources/navbar.jsp"%>

<title>Application</title>
<link rel="stylesheet" type="text/css" href="/resources/header-footer.css">
<body>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<div class="svg-container">
    <!-- I crated SVG with: https://codepen.io/anthonydugois/pen/mewdyZ -->
    <svg viewbox="0 0 1650 400" class="svg">
        <path id="curve" fill="#50c6d8" d="M 1650 300 Q 400 350 0 300 L 0 0 L 1650 0 L 1650 300 Z">
        </path>
    </svg>
</div>

<header>
    <h1>This is a petition request page</h1>
    <h3>Here you are, scroll down.</h3>
</header>

<main>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css">
    <div class="square">
        <div class="form-style-6">
            <h1>Petition for Internship</h1>
            <form id="create_petition" name="create_petition" method="post" action="${pageContext.request.contextPath}/student/create_petition_process">
                <input required type="text" name="title" placeholder="Petition Title" />
                <%--SELECT INTERNSHIP--%>
                <select required name="company" id="company" label="--select an internship--">
                    <c:forEach var="company" items="${companies}">
                        <c:if test="${company.companyName != 'admin'}"> <%--company with 'admin' as name is a mock company used by admin--%>
                            <optgroup label="${company.companyName}">
                                <c:forEach var="inter" items="${company.internships}">
                                    <c:if test="${inter.status != 'accepted'}">
                                        <option value="${inter.title}" label="${inter.title}"> ${inter.title} </option>
                                    </c:if>
                                </c:forEach>
                            </optgroup>
                        </c:if>
                    </c:forEach>
                    </select>
                <textarea required name="description" placeholder="Description and Why Should We Choose You"></textarea>
                <input type="text" name="comments" placeholder="Additional Comments">
                <input type="submit" value="Submit" onsubmit="console.log('send')" />
            </form>
            <div id="bottom1"></div>
        </div>
    </div>
</main>

<footer>
    <p>All rights reserved... Team52 Co.</p>
</footer>
</body>

<script type="text/javascript">
    $(document).ready(function () {
        var form =$("#create_petition").submit(function (event) {
            event.preventDefault();
            // console.log(form);

            var $form = $(this),
                url = $form.attr('action');
            console.log("posting happens");

            $.ajax({
                type: "POST",
                url: url,
                data : $("#create_petition").serialize(),
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
                    // console.log(data);

                    console.log("submission finished");
                }
            });
            event.preventDefault();
        });
    });
</script>
</body>
</html>
