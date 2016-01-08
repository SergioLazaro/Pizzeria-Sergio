/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Web.AdminOrders;
import Web.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergiolazaromagdalena
 */
public class userOrders extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = request.getParameter("error");
        try {            
            Facade facade = new Facade();
            List<AdminOrders> list = facade.getOrders();
            //Get all cookies to get the user who is logged in
            Cookie []cookies = request.getCookies();
            String user = getUsernameCookie(cookies);
            //Starting printing user orders
            PrintWriter out = response.getWriter();
            checkError(error, out);      //Checking for errors
            System.out.println("ERROR CHECKED");
            out.println("<div class=\"pizza-block\">");
            boolean orders = false;
            int orderID = 0;    //Alternate if orderID change
            boolean background = true;  //If grey-background then true
            out.println("<h2>" + user + "</h2></br>");
            for(AdminOrders o : list){
                if(o.getUsername().equals(user)){
                    //IF-ELSE needed for change the background
                    if(orderID == 0){  //First time
                        orderID = o.getOrderID();
                        out.println("<div id=\"grey-background\">");
                        //Insert the form petition to delete this userOrder 
                        out.println("<form method=\"POST\" action=\"../cancelOrder\">");
                    }
                    else{   //Not the first one
                        if(orderID != o.getOrderID()){  //Different order
                            orderID = o.getOrderID();
                            //Add the button and close the form and div blocks
                            out.println("<button class=\"btn btn-lg btn-primary btn-block\"" +
                                "type=\"submit\">Cancel order</button>");
                            out.println("</form>");
                            out.println("</div>");
                            if(background){
                                background = false;
                                out.println("<div>");
                                //Insert the form petition to delete this userOrder 
                                out.println("<form method=\"POST\" action=\"../cancelOrder\">");
                            }
                            else{
                                background = true;
                                out.println("<div id=\"grey-background\">");
                                //Insert the form petition to delete this userOrder 
                                out.println("<form method=\"POST\" action=\"../cancelOrder\">");
                            }
                        }
                        else{   //Print horizontal line to separate 
                            out.println("<hr>");
                        }
                    }
                    //Just printing the order info
                    orders = true;
                    out.println("<input type=\"hidden\" name=\"orderid\" value=\"" 
                            + o.getOrderID() + "\">");
                    out.println("<p>Order number: " +o.getOrderID() + "</p></br>");
                    out.println("<p>Pizza name: " +o.getPizzaName() + "</p></br>");
                    out.println("<p>Ingredients: " +o.getIngredients() + "</p></br>");
                    out.println("<p>Quantity: " +o.getQuantity() + "</p></br>");
                    out.println("<p>Total price: " + 
                            o.getPrice() * o.getQuantity() + " euros</p></br>");
                    out.println("<p>Date: " +o.getDate() + "</p></br>");
                }
            }
            if(!orders){
                out.println("<h4> User " + user + " dind't ask for pizza.");
                out.println("</div>"); 
            }
            else{
               //Add the button and close the form and div blocks
                out.println("<button class=\"btn btn-lg btn-primary btn-block\"" +
                    "type=\"submit\">Cancel order</button>");
                out.println("</form>");
                out.println("</div>"); 
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * 
     * @param cookies is the array of cookies and must be different to null
     * @return the value of username cookie
     */
    private String getUsernameCookie(Cookie[] cookies){
        Cookie username = null;
        // Get an array of Cookies associated with this domain
        if( cookies != null ){  //We have cookies
            for (int i = 0; i < cookies.length; i++){   //Looking for username
                if(cookies[i].getName().equals("username")){
                    username = cookies[i];
                }
            }
        }
        return username.getValue();
    }
    
    /**
     * 
     * @param error
     * @param out must be distinct to null if error is not null
     * Method that check if we have error parameter initialized
     * If it is initialized, show an alert depending on error value
     * If it is not initialized, this method does nothing
     */
    private void checkError(String error, PrintWriter out){
        System.out.println("ERRORVALUE: " + error);
        if(error != null){  //Check if error was initialized
            int errorValue = Integer.parseInt(error);
            System.out.println("ENTER checkError");
            if(errorValue <= 0){    //Error situation
                System.out.println("ERRORS");
                out.println("<div class=\"alert alert-danger\">");
                out.println("<a class=\"close\" data-dismiss=\"alert\" "
                        + "aria-label=\"close\">&times;</a>");
                out.println("<strong>Error!</strong> Order could not be deleted.");
                out.println("</div>");
            }
            else{   //Deleted 1 or more rows
                System.out.println("DELETED ROWS");
                out.println("<div class=\"alert alert-success\">");
                out.println("<a class=\"close\" data-dismiss=\"alert\" "
                        + "aria-label=\"close\">&times;</a>");
                out.println("<strong>Success!</strong> Order deleted successfuly.");
                out.println("</div>");
            }
        }
        
    }
}
