/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

/**
 *
 * @author sergiolazaromagdalena
 */
public class AdminOrders {
    
    private int orderID;
    private String username;
    private String ingredients;
    private int quantity;
    private double price;
    private String date;
    private String pizzaName;
    
    
    public AdminOrders(int orderID, String username, String pizzaName, 
            String ingredients, int quantity, double price, String date){
        this.orderID = orderID;
        this.username = username;
        this.pizzaName = pizzaName;
        this.ingredients = ingredients;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        
    }

    public int getOrderID() {
        return orderID;
    }

    public String getUsername() {
        return username;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
    
    
}
