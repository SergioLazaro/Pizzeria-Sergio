<%-- 
    Document   : orders
    Created on : 02-ene-2016, 20:07:58
    Author     : sergiolazaromagdalena
--%>
<jsp:include page="top.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="main-container"></div>

<script>
    $(document).ready(function(){
      $.get("../orders",function(data){
          $('.main-container').html(data); 
     }); 
   });
</script>
<jsp:include page="bottom.html"/>

