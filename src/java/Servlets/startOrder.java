/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Web.Facade;
import Web.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class startOrder extends HttpServlet {

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
        int i = 1;
        boolean finish = false;
        PrintWriter out = response.getWriter();
        ArrayList<Order> array = new ArrayList<Order>();
        String username = getUsernameCookie(request.getCookies());  //Getting the username who is logged in
        while(!finish){
            String pizzaID = request.getParameter("selector"+i);
            if(pizzaID != null){
                String quantity = request.getParameter("quantitySelector" + i);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
                array.add(new Order(Integer.parseInt(pizzaID) + 1,Integer.parseInt(quantity),dateFormat.format(date),username));
                i++;
            }
            else{
                finish = true;
            }
        }
        Facade facade = new Facade();
        try {
            int success = facade.addOrder(array);
            response.sendRedirect("pages/index.jsp?error=" + success);
        } catch (Exception ex) {
            Logger.getLogger(startOrder.class.getName()).log(Level.SEVERE, null, ex);
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
