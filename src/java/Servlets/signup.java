/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Web.Facade;
import Web.User;
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
 * @author Sergio
 */
public class signup extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        int val = 2;
        if(checkSQLInjection(username) && checkSQLInjection(password) &&
                checkSQLInjection(repassword)){
            if(password.equalsIgnoreCase(repassword)){ 
                try {
                    Facade facade = new Facade();
                    val = facade.addUser(username, password);
                    if(val == 1){
                        Cookie userCookie = new Cookie("username",username);
                        userCookie.setMaxAge(60*60*24*365);
                        Cookie roleCookie = new Cookie("role","user");
                        roleCookie.setMaxAge(60*60*24*365);
                        response.addCookie(userCookie);
                        response.addCookie(roleCookie);
                        response.sendRedirect("pages/index.jsp");
                    }  
                } catch (Exception ex) {
                    Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        response.sendRedirect("pages/index.jsp?error=" + val);
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
