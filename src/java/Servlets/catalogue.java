/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Web.Facade;
import Web.Pizza;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergiolazaromagdalena
 */
public class catalogue extends HttpServlet {


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
        List<Pizza> list;
        Facade facade = new Facade();
        try {
            list = facade.getCatalogue();
            PrintWriter out = response.getWriter();
            out.println("<div class=\"pizza-block\">");
            int count = 0;
            for(Pizza p : list){
                if(count % 2 == 0){
                    out.println("<div id=\"grey-background\">");
                    out.println("<p>Name: "+ p.getName() + "</p>");
                    out.println("<p>Price: "+ p.getPrice()+ " euro(s)</p>");
                    out.println("<p>Ingredients: "+ p.getIngredients() + "</p>");
                    out.println("</div>");
                }
                else{
                    out.println("<div>");
                    out.println("<p>Name: "+ p.getName() + "</p>");
                    out.println("<p>Price: "+ p.getPrice()+ " euro(s)</p>");
                    out.println("<p>Ingredients: "+ p.getIngredients() + "</p>");
                    out.println("</div>");
                }
                count ++;
            }
            out.println("</div>");
        } catch (Exception ex) {
            Logger.getLogger(catalogue.class.getName()).log(Level.SEVERE, null, ex);
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

}
