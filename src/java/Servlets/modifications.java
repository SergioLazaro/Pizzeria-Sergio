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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class used by the 'admin' user to insert/modify/delete a pizza
 * @author sergiolazaromagdalena
 */
public class modifications extends HttpServlet {


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
        String option = request.getParameter("optradio");   //Getting selected option
        
        //If admin want to modify/delete a pizza
        if(option.equals("modify") || option.equals("delete")){    // Modify or Delete option
            String pizzaID = request.getParameter("pizzaSelector");
            String price = request.getParameter("price");
            String ingredients = request.getParameter("ingredients");
            System.err.println("Price: " + price + " - Ingredients: " + ingredients);
            if(!price.equals("") && !ingredients.equals("")){   //Modify option
                if(checkSQLInjection(price) && checkSQLInjection(ingredients)){
                    Facade facade = new Facade();
                    try{
                        int val = facade.modifyPizza(pizzaID,price,ingredients);
                        response.sendRedirect("pages/modifications.jsp?error=" + val);

                    } catch (Exception ex) {
                        Logger.getLogger(modifications.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            }
            else{   //Delete option
                Facade facade = new Facade();
                try{
                    int val = facade.deletePizza(pizzaID);
                    response.sendRedirect("pages/modifications.jsp?error=" + val);
                
                } catch (Exception ex) {
                    Logger.getLogger(modifications.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{   //Insert option
            String name = request.getParameter("pizza");
            String price = request.getParameter("price");
            String ingredients = request.getParameter("ingredients");
            if(checkSQLInjection(name) && checkSQLInjection(price) 
                    && checkSQLInjection(ingredients)){
                Facade facade = new Facade();
                try {
                    int val = facade.insertPizza(name, price, ingredients);
                    response.sendRedirect("pages/modifications.jsp?error=" + val);
                } catch (Exception ex) {
                    Logger.getLogger(modifications.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
        
    }
    /**
     * Method used to check if user tries to modify the DB with SQLInjection
     * @param data is the String inserted by the user
     * @return true if and only if 'data' does not contain SQL sentences
     */
    private boolean checkSQLInjection(String data){
        data = data.toLowerCase();
        boolean go = false;
        if(!data.contains("drop") || !data.contains("alter table") ||
                !data.contains("insert") || !data.contains("delete")
                || !data.contains("update")){
            go = true;
        }
        return go;
    }

}
