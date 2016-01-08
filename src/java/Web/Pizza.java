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
public class Pizza {
    
    private int idPizza;
    private String name;
    private double price;
    private String ingredients;

    
    public Pizza(int idPizza, String name, double price, String ingredients){
           this.idPizza = idPizza;
           this.name = name;
           this.price = price;
           this.ingredients = ingredients;
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    
    @Override
    public String toString() {
        return "Pizza{" + "idPizza=" + idPizza + ", name=" + name + ", price=" + price + ", ingredients=" + ingredients + '}';
    }
}
