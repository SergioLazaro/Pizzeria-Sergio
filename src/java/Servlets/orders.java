/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Web.AdminOrders;
import Web.Facade;
import Web.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergiolazaromagdalena
 */
public class orders extends HttpServlet {

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
        try {
            Facade facade = new Facade();
            List<AdminOrders> list = facade.getOrders();
            List<String> users = facade.getUsernames();
            PrintWriter out = response.getWriter();
            out.println("<div class=\"pizza-block\">");
            boolean orders = false;
            int orderID = 0;    //Alternate if orderID change
            boolean background = true;  //If grey-background then true
            boolean change = false;
            for(String user : users){
                out.println("<h2>" + user + "</h2></br>");
                orderID = 0;
                for(AdminOrders o : list){
                    if(o.getUsername().equals(user)){
                        //IF-ELSE needed for change the background
                        if(orderID == 0){  //First time
                            orderID = o.getOrderID();
                            out.println("<div id=\"grey-background\">");
                        }
                        else{   //Not the first one
                            if(orderID != o.getOrderID()){  //Different order
                                orderID = o.getOrderID();
                                out.println("</div>");
                                if(background){
                                    background = false;
                                    out.println("<div>");
                                }
                                else{
                                    background = true;
                                    out.println("<div id=\"grey-background\">");
                                }
                            }
                        }
                        orders = true;
                        System.out.println("Background: " + background + 
                                " - " + orderID);
                        out.println("<p>Order number: " +o.getOrderID() + "</p></br>");
                        out.println("<p>Pizza name: " +o.getPizzaName() + "</p></br>");
                        out.println("<p>Ingredients: " +o.getIngredients() + "</p></br>");
                        out.println("<p>Quantity: " +o.getQuantity() + "</p></br>");
                        out.println("<p>Total price: " +o.getPrice() + " euros</p></br>");
                        out.println("<p>Date: " +o.getDate() + "</p></br>");
                        
                    }
                }
                if(!orders){
                    out.println("<h4> User " + user + " dind't ask for pizza.");
                }
                orders = false;
                orderID = 0;
            }
            out.println("</div>");
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
    
    private void printOrder(AdminOrders o, PrintWriter out){
        
    }

}
