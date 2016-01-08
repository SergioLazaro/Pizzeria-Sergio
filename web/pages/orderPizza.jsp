<%-- 
    Document   : orders
    Created on : 02-ene-2016, 20:07:58
    Author     : sergiolazaromagdalena
--%>
<jsp:include page="top.jsp"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="../js/orderPizza.js"></script>
<div class="main-container">
    <%
        // Get an array of Cookies associated with this domain
        Cookie[] cookies = null;
        cookies = request.getCookies();
        Cookie username = null;
        if( cookies != null ){
           for (int i = 0; i < cookies.length; i++){
              if(cookies[i].getName().equals("username")){
                  username = cookies[i];
              }
           }
        }
        if(username != null){   //If user has logged in
            %>
            <div class="pizza-block" id="grey-background">
                <form action="../startOrder" method="POST">
                    <h2> Choose your pizzas</h2></br>
                    <div id="order"></div></br>
                    <table style="width: 100%;">
                        <tr>
                            <td><button class="btn btn-lg btn-primary btn-block" 
                                        id="add-button" type="button">Add more</button></td>
                            <td><button class="btn btn-lg btn-primary btn-block" 
                                        type="submit">Confirm</button></td>
                        </tr>
                    </table>

                </form>
            </div>
            <%
        }
        else{
            response.sendRedirect("login.jsp?error=1");
        }
    %>
</div>


<jsp:include page="bottom.html"/>

