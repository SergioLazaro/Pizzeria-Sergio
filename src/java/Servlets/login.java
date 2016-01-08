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
public class login extends HttpServlet {


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
        int val = 3;
        if(checkSQLInjection(username) && checkSQLInjection(password)){
            try {
                Facade facade = new Facade();
                User user = facade.checkUser(username,password);
                if(user != null){
                    Cookie userCookie = new Cookie("username",user.getUsername());
                    userCookie.setMaxAge(60*60*24*365);
                    Cookie roleCookie = new Cookie("role",user.getRole());
                    roleCookie.setMaxAge(60*60*24*365);
                    response.addCookie(userCookie);
                    response.addCookie(roleCookie);
                    response.sendRedirect("pages/index.jsp");
                }
                else{
                    response.sendRedirect("pages/index.jsp?error=" + val);
                }
            } catch (Exception ex) {
                Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
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
