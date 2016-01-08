/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import java.util.Date;

/**
 *
 * @author sergiolazaromagdalena
 */
public class Order {
    
    private int pizzaID;
    private int quantity;
    private String date;
    private String username;

    
    public Order(int pizzaID, int quantity, String date, String username){
        this.pizzaID = pizzaID;
        this.quantity = quantity;
        this.date = date;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPizzaId() {
        return pizzaID;
    }

    public void setPizzaId(int pizzaID) {
        this.pizzaID = pizzaID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
    @Override
    public String toString() {
        return "Order{" + "pizzaID=" + pizzaID + ", quantity=" + quantity + ", date=" + date + ", username=" + username + '}';
    }
}
