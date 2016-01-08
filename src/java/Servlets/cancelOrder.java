/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Web.Facade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergiolazaromagdalena
 */
public class cancelOrder extends HttpServlet {

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
        String orderID = request.getParameter("orderid");
        Cookie []cookies = request.getCookies();
        String username = getUsernameCookie(cookies);
        try{
            Facade facade = new Facade();
            int val = facade.deleteOrder(orderID,username);
            response.sendRedirect("pages/userOrders.jsp?error=" + val);
        }
        catch(Exception ex){
            Logger.getLogger(catalogue.class.getName()).log(Level.SEVERE, null, ex);

        }
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

}
