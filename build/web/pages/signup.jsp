<%@ include file="top.jsp"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="main-container">
    <div class="main-container-signin">
        <form class="form-signin" action="../signup" method="POST">
            <h2> Please sign up</h2>
            <label class="sr-only">Username</label>
            <input name="username" class="form-control" type="text" autofocus="" required placeholder="Username"/>
            <label class="sr-only">Password</label>
            <input name="password" class="form-control" type="password" autofocus="" required placeholder="Password"/>
            <label class="sr-only">RePassword</label>
            <input name="repassword" class="form-control" type="password" autofocus="" required placeholder="Retype Password"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
        </form>
    </div>
</div>
<%@ include file="bottom.html"%>