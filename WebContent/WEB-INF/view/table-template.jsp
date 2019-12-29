<%@ page contentType="text/html;charset=UTF-8" %>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Selectable - Display as grid</title>

<%--    Milligram css framework (stupendously light)--%>
<%--    <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:300,300italic,700,700italic">--%>


<%--    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.css">--%>


<%--    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/milligram/1.3.0/milligram.css">--%>


    <!-- Pure css framework -->
<%--  <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.1/build/pure-min.css" integrity="sha384-oAOxQR6DkCoMliIh8yFnu25d7Eq/PHS21PClpwjOTeU2jRSq11vu66rf90/cZr47" crossorigin="anonymous">--%>

  <!-- styling for the datatable -->
  <link rel="stylesheet" type="text/css" href=" https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css ">
  <link rel="stylesheet" type="text/css" href=" https://cdn.datatables.net/1.10.20/css/dataTables.jqueryui.min.css ">


    <%--  jquery sources  --%>
  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <%--  scripts sources for jquery data tables --%>
  <script type="text/javascript" src=" https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.jqueryui.min.js"></script>



  <style>

    

      td {border: 1px #DDD solid; padding: 5px; cursor: pointer;}
/*
      tr.selected td {
          background-color: #333;
          color: #fff;    
      }
*/
      .l-box {
          padding: 1em;
      } 

      .hovered td {
          background: #ddd;
      }   
         
  </style>

</head>
<body>


  
<table id="example" class="display"  align="center">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            <tr>
                <td>Garrett Winters</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>63</td>
                <td>2011/07/25</td>
                <td>$170,750</td>
            </tr>
            <tr>
                <td>Ashton Cox</td>
                <td>Junior Technical Author</td>
                <td>San Francisco</td>
                <td>66</td>
                <td>2009/01/12</td>
                <td>$86,000</td>
            </tr>
        </tbody>
  </table>


  <input type="button" name="myb" id="myb" value="click me">


<script type="text/javascript">

    $('example tr').mouseover(function() {    //on hover color script
      $(this).addClass('hovered');
    }).mouseout(function() {
      $(this).removeClass('hovered');
    });

  function highlight(e) {                           //highlight function
    if (selected[0]) selected[0].className = '' 
      e.target.parentNode.className = 'selected';

    
    // if (selected[0]) selected[0].className = 'selected' 
       // e.target.parentNode.className = '';

    
    // var v= $(this).find(".selected td:first").html()
    
  }

  function submit() {               
                                                  //on button click sumbit function
    var v = $('.selected td').eq(2).text();             //get selected tablerow's second column (starts from zero)
    console.log(v);
    
  }

  var table = document.getElementById('example');

  var selected = table.getElementsByClassName('selected');

  table.onclick = highlight;  

  myb.onclick = submit;
  


  


  
$(document).ready(function() {
  var table = $('#example').DataTable();    //make "example" into a datatable using the library



  
  
} );
</script>
 

</body>
</html>