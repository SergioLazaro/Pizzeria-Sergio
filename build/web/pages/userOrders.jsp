<%-- 
    Document   : userOrders
    Created on : 08-ene-2016, 9:54:32
    Author     : sergiolazaromagdalena
--%>

<jsp:include page="top.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="main-container"></div>

<script>
    $(document).ready(function(){
      $.get("../userOrders",function(data){
          $('.main-container').html(data); 
     }); 
   });
</script>
<jsp:include page="bottom.html"/>
