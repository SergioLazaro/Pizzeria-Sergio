<%-- 
    Document   : modifications
    Created on : 07-ene-2016, 11:02:57
    Author     : sergiolazaromagdalena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"/>
<script src="../js/manageFields.js"></script>
<div class="main-container">
    <%
        String error = request.getParameter("error");
        if(error != null && error.equals("1")){
            %>
                <div class="alert alert-success">
                    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong> New pizza added successfuly.
                </div>
            <%
        }
        else if(error != null){
            %>
                <div class="alert alert-warning">
                    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Warning!</strong> There was an error inserting the pizza.
                </div>
            <%
        }
    %>
    <div class="pizza-block" id="grey-background">
        <form class="form-signin" action="../modifications" method="POST">
            <h2> Modify the catalogue</h2>
            <p>Choose your modification: </p>
            <div class="radio-inline-panel">
              <label class="radio-inline">Insert pizza </label><input type="radio" name="optradio" id="insertselected" value="insert" onclick="check();"/>
              <label class="radio-inline">Modify pizza </label><input type="radio" name="optradio" id="modifyselected" value="modify" onclick="check();"/>
              <label class="radio-inline">Delete pizza </label><input type="radio" name="optradio" id="deleteselected" value="delete" onclick="check();"/>
            </div>
            </br>
            <div id="pizzaSelector">          
                <label class="sr-only">Pizza type</label>
                <select class="form-control" id="selector" name="pizzaSelector"></select>
            </div> 
            <div id="pizzaInput">          
                <label class="sr-only">Pizza name</label>
                <input id="pizzaInputField" name="pizza" class="form-control" type="text" autofocus="" placeholder="Pizza name"/>
            </div>
            <div id="priceInput">          
                <label class="sr-only">Price</label>
                <input id="priceInputField" name="price" class="form-control" type="text" autofocus="" placeholder="Price"/>
            </div> 
            <div id="ingredientsInput">          
                <label class="sr-only">Ingredients</label>
                <input id="ingredientsInputField" name="ingredients" class="form-control" type="text" autofocus="" placeholder="Ingredients"/>
            </div> 
            <div id="button">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Confirm</button>
            </div>
        </form>
    </div>   
</div>       
<%@ include file="bottom.html"%>

