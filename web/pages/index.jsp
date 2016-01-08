<%@ include file="top.jsp" %>
<div class="main-container">
    <% 
        // Get an array of Cookies associated with this domain
        cookies = request.getCookies();
        if( cookies != null ){
           for (int i = 0; i < cookies.length; i++){
              if(cookies[i].getName().equals("username")){
                  username = cookies[i];
              }
           }
        }
        
        String error = request.getParameter("error");
        if(error != null && error.equals("0")){
            %>
                <div class="alert alert-success">
                    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong> Order sent successfuly.
                </div>
            <%
        }
        else if(error != null && error.equals("1")){
        %>
            <div class="alert alert-success">
                <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Success!</strong> User <% username.getValue(); %>
                created successfuly.
            </div>
        <%
        }
        else if(error != null && error.equals("2")){ 
        %>
            <div class="alert alert-danger">
                <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Error!</strong> There was a problem trying to create the user.
            </div>
        <%
        }
        else if(error != null && error.equals("3")){
            %>
                <div class="alert alert-danger">
                    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Error!</strong> There was a problem trying to login.
                </div>
            <%
        }
        else if(error != null && error.equals("4")){
            %>
            <div class="alert alert-danger">
                    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Error!</strong> There was a problem sending your order.
                </div>
            <%
        }
    %>
    <div id= "carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="5000">
        <ol class="carousel-indicators">
            <li data-slide-to="0" data-target="#carousel-example-generic"></li>
            <li data-slide-to="1" data-target="#carousel-example-generic"></li>
            <li data-slide-to="2" data-target="#carousel-example-generic"></li>
            <li data-slide-to="3" data-target="#carousel-example-generic"></li>
            <li data-slide-to="4" data-target="#carousel-example-generic"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img id="photo1" src="../images/pizza1.jpg" data-holder-rendered="true" alt="Slide num. 1">
            </div>
            <div class="item">
              <img id="photo2" src="../images/pizza2.jpg" data-holder-rendered="true" alt="Slide num. 2">
            </div>
            <div class="item">
              <img id="photo3" src="../images/pizza3.jpg" data-holder-rendered="true" alt="Slide num. 3">
            </div>
            <div class="item">
              <img id="photo4" src="../images/pizza4.jpg" data-holder-rendered="true" alt="Slide num. 4">
            </div>
            <div class="item">
              <img id="photo5" src="../images/pizza5.jpg" data-holder-rendered="true" alt="Slide num. 5">
            </div>
        </div>
          <a class="left carousel-control" data-slide="prev" role="button" href="#carousel-example-generic">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          </a>
          <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          </a>
     </div>
    <hr class="featurette-divider"> 
  <div class="main-container-bottom">
    <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" height="140" width="140">
          <h2>See our catalogue</h2>
          <p>Have a look on our pizzas and their ingredients.
          </p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" height="140" width="140">
          <h2>Choose your pizzas</h2>
          <p>Select one or more pizzas in our catalogue.
          </p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" height="140" width="140">
          <h2>Confirm or decline your orders</h2>
          <p>If you change your opinion, don't worry. You can decline or change
              your orders.
          </p>
        </div><!-- /.col-lg-4 -->
      </div>
      <hr class="featurette-divider">
    </div>
     </div>
</div>
<%@ include file="bottom.html" %>