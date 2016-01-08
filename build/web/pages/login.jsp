<%@ include file="top.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="main-container">
    <%
        String error = request.getParameter("error");
        if(error != null && error.equals("1")){
            %>
                <div class="alert alert-warning">
                    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Warning!</strong> You have to log in first.
                </div>
            <%
        }
    %>
    <div class="main-container-signin">
        <form class="form-signin" action="../login" method="POST">
            <h2> Please sign in</h2>
            <label class="sr-only">Username</label>
            <input name="username" class="form-control" type="text" autofocus="" required placeholder="Email"/>
            <label class="sr-only">Password</label>
            <input name="password" class="form-control" type="password" autofocus="" required="" placeholder="Password"/>          
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </div>
</div>
<%@ include file="bottom.html"%>