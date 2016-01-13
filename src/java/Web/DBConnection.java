/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DB Connection Class
 * @author sergiolazaromagdalena
 */
public class DBConnection {
    
    private static final String url = "jdbc:mysql://localhost:3306/Pizzeria";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    
    public DBConnection(){}
    
    public Connection startConnection(){
        Connection conn = null;
        try {
            //executeUpdate to insert/modify data
            //executeQuery to see data
            //Statement st = conn.createStatement();
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url,user,pass);
            
            //String query = "INSERT INTO user VALUES('" + username + "','" + pass + "','" + role +"')";
            //int val = st.executeUpdate(query); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
