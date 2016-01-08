<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Pizzeria</title>

    <!-- Bootstrap -->
      
    <link href="../frameworks/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../frameworks/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/mystyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/css/bootstrap-select.min.css">
    <!-- JQuery -->
    <script src="http://ajax.googleapis.com/ajax/libs/prototype/1.7.0.0/prototype.js"
            type="text/javascript"></script>
    <script src="http://www.cs.washington.edu/education/courses/cse190m/12sp/homework/7/grading.js"
            type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
  </head>
  <body>
        <% 
            Cookie username = null;
            Cookie role = null;
            Cookie[] cookies = null;
            // Get an array of Cookies associated with this domain
            cookies = request.getCookies();
            if( cookies != null ){  //We have cookies
                for (int i = 0; i < cookies.length; i++){   //Looking for username
                    if(cookies[i].getName().equals("username")){
                        username = cookies[i];
                    }
                    if(cookies[i].getName().equals("role")){
                        role = cookies[i];
                    }
                }
            }
        %>
      <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" aria-controls="navbar" 
                    data-target="#navbar" data-toggle="collapse" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Pizzeria</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
            <ul class="nav navbar-nav">
                <li id="home-nav"><a href="catalogue.jsp">Catalogue</a></li>
                <li id="post-nav"><a href="orderPizza.jsp">Order pizza</a></li>
                <% 
                   if(role != null && role.getValue().equals("admin")){
                   %>
                        <li id="post-nav"><a href="orders.jsp">Orders</a></li>
                        <li id="post-nav"><a href="modifications.jsp">Modify catalogue</a></li>
                   <%
                   }
                   else if(role != null && role.getValue().equals("user")){
                   %>
                        <li id="post-nav"><a href="userOrders.jsp">See orders</a></li>
                   <%
                   }
                %> 
                <li id="about-nav"><a href="about.jsp">About</a></li>
            </ul>
                <%
                    if(username == null){ //There are not cookies initialized
                %>
                        <div class="navbar-form navbar-right" id="login-actions">
                            <div class="form-group">
                                <button class="btn btn-primary" id="loginbutton">Log in</button>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" id="signupbutton">Sign up</button>
                            </div>
                        </div>
                    <%
                }else{  //We have a user cookie
                    %>
                    <div class="navbar-form navbar-right" id="login-actions">
                        <p id="cookie"> Welcome, <%= username.getValue() %> 
                            (<a href="../deleteCookie?delete=username">Logout</a>)
                        </p>
                    </div>
                <%
                }
            %>      
        </div>       
        </div>
    </nav>
		