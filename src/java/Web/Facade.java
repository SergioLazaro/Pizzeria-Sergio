package Web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Facade {
	
	public Facade(){}
	
        /**
         * @return a List<String> which contains the name of every user 
         * @throws Exception if there is a problem with the DB connection
         * 'SQLException'
         */
        
        public List<String> getUsernames() throws Exception{
            Connection mysql = null;
            List<String> users = new ArrayList<String>();
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement(); 
                ResultSet res = st.executeQuery("SELECT username FROM user WHERE username != 'admin'");
                String username = "";
                
		while (res.next()) {
                    username = res.getString("username");
                    users.add(username);
		}
                System.err.println("USERS: " + users.size());
                return users;
            } catch (Exception e) {
                return null;
            } finally {
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method used to insert a new user(username,password,role) in the DB
         * @param username should be a not empty String
         * @param password should be a not empty String
         * @return an integer value depending on the result of adding the new
         * user. This value is used to show feedback to the user.
         * @throws Exception if there is a problem with the DB connection 
         */
        public int addUser(String username, String password) throws Exception{
            Connection mysql = null;
            int val = 2;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement(); 
                //Check if there is a user with the same username
                ResultSet res = st.executeQuery("SELECT COUNT(*) as count FROM user "
                        + "WHERE username = '" + username + "' AND"
                        + "password = '" + password +"'");
                
                int found = 0;
		while (res.next()) {
                    found = res.getInt("count");
		}
                System.out.println("CREATE NEW USER: " + found);
                if(found == 0){  //If there is not a user
                    System.out.println("CREATE NEW USER");
                    String query = "INSERT INTO user(username,password,role) "
                            + "VALUES('" + username + "','" +  password +
                            "','user')";
                    val = st.executeUpdate(query);
                }
            } catch (Exception e) {
                return 2;
            } finally {
                if(mysql != null){
                    mysql.close();
                }
            }
            return val;
	}
        
        /**
         * Check a user role
         * @param username should be a not empty String
         * @param password should be a not empty String
         * @return the User with (username,password)
         * @throws Exception (SQLException)
         */
        public User checkUser(String username, String password) throws Exception{
            Connection mysql = null;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement();
                ResultSet res = st.executeQuery("SELECT * FROM"
                        + " user WHERE username = '" + username + "'");
                User newUser = null;
		while (res.next()) {    // There are data
                    String role = res.getString("role");
                    System.out.println("ROLE: " + role);
                    newUser = new User(username,password,role);
		}
		return newUser;	
            } catch (Exception e) {
                    return null;
            } finally {
                if(mysql != null){
                    mysql.close();
                }
            }
            
	}
        
        /**
         * Method which is used to get every pizza in the catalogue
         * @return a List<Pizza> used to show in <select> tag
         * @throws Exception (SQLException)
         */
        public List<Pizza> getCatalogue() throws Exception{
            Connection mysql = null;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement(); 
                ResultSet res = st.executeQuery("SELECT * FROM pizza");
                List<Pizza> list = new ArrayList<Pizza>();
                int idPizza;
                double price;
                String name, ingredients;
                while(res.next()){  //Iterate until insert all pizzas in 'list'
                    idPizza = res.getInt("idPizza");
                    name = res.getString("name");
                    price = res.getDouble("price");
                    ingredients = res.getString("ingredients");
                    list.add(new Pizza(idPizza,name,price,ingredients));
                }
                return list;
            }
            catch(Exception e){
                return null;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method used to get the ingredients of a pizza
         * @param pizza should be the name of a pizza inserted in the DB
         * @return a Pizza object
         * @throws Exception (SQLException)
         */
        public Pizza getIngredients(String pizza) throws Exception{
            Connection mysql = null;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement(); 
                ResultSet res = st.executeQuery("SELECT * FROM pizza"
                        + " WHERE name = '" + pizza + "'");
                String ingredients = "";
                double price = 0.0;
                int pizzaID = 0;
                while(res.next()){
                     price = res.getDouble("price");
                     ingredients = res.getString("ingredients");
                     pizzaID = res.getInt("pizzaID");
                 }
                //returning the Pizza object
                return new Pizza(pizzaID,pizza,price,ingredients);
            }
            catch(Exception e){
                return null;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method that inserts into the DB a new order done by users.
         * @param array should be a non empty ArrayList of Orders.
         * @return a integer used to show feedback to the users
         * @throws Exception 
         */
        public int addOrder(ArrayList<Order> array)throws Exception{
            Connection mysql = null;
            int inserted = 0;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement();
                ArrayList<Pizza> pizzaArray = getPizzas(st);
                //Get MAX orderID of user
                int max = getLastOrder(array.get(0).getUsername(),st);
                max++;
                
                //Inserting every pizza in the same orderID
                for(Order o : array){
                    Pizza pizza = getPizzaById(o.getPizzaId(),pizzaArray);
                    String query = "INSERT INTO pizzaOrder(orderID,username,"
                            + "idPizza,quantity,price,orderDate) "
                            + "VALUES(" + max + ",'" + o.getUsername() + "'," + 
                            pizza.getIdPizza() + "," + o.getQuantity() 
                            + "," + pizza.getPrice() + ",'" + o.getDate() + "')";
                    st.executeUpdate(query);
                    inserted ++;
                }
                if(inserted == array.size()){
                    return 1;
                }
                else{
                    return 4;
                }
                
            }
            catch(Exception e){
                return 4;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method that looks for in array for the Pizza object with the pizzaID
         * @param pizzaId should be an integer distinct to 0
         * @param array should be an ArrayList of Pizza objects and not empty
         * @return the Pizza object with pizzaId if it is contained in 'array'
         */
        private Pizza getPizzaById(int pizzaId, ArrayList<Pizza> array){
            Pizza pizza = null;
            for(Pizza p : array){
                System.out.println(p.getIdPizza() + " - " + pizzaId);
                if(p.getIdPizza() == pizzaId){
                    pizza = p;
                }
            }
            return pizza;
        }
        
        /**
         * Method used to get every Pizza object inserted in the DB
         * @param st should be a Statement object initialized
         * @return an ArrayList of Pizza objects
         * @throws Exception (SQLException)
         */
        private ArrayList<Pizza> getPizzas(Statement st) throws Exception{
            ResultSet res = st.executeQuery("SELECT * FROM pizza");
            ArrayList<Pizza> pizzaArray = new ArrayList<Pizza>();
            //Pizza variables
            String ingredients = "";
            String name = "";
            double price = 0.0;
            int pizzaID = 0;
            int i = 0;
            while(res.next()){  //Populate array with pizzas
                price = res.getDouble("price");
                ingredients = res.getString("ingredients");
                pizzaID = res.getInt("idPizza");
                name = res.getString("name");
                pizzaArray.add(new Pizza(pizzaID,name,price,ingredients));
                i++;
            }
            return pizzaArray;
        }
        
        /**
         * Method that look for a username last order in the DB.
         * @param username should be a non empty String
         * @param st should be a Statement object initiliazed
         * @return an integer value equal to the last order ordered by the user
         * with 'username' as username.
         * @throws Exception (SQLException)
         */
        private int getLastOrder(String username, Statement st) throws Exception{
            ResultSet res = st.executeQuery("SELECT max FROM lastOrderPerUser " +
                    "WHERE username = '" + username + "'");
            int id = 0;
            while(res.next()){
                id = res.getInt("max");
            }
            return id;
        }
	
        /**
         * Method used to show to the admin user every order inside the DB
         * @return a List of AdminOrders
         * @throws Exception (SQLException)
         */
        public List<AdminOrders> getOrders() throws Exception{
            Connection mysql = null;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement(); 
                ResultSet res = st.executeQuery("SELECT * FROM pizzaOrder t1 ,"
                        + " pizza t2 WHERE t1.idPizza = t2.idPizza" 
                        + " ORDER BY orderID, username ASC");
                List<AdminOrders> list = new ArrayList<AdminOrders>();
                int orderID, quantity;
                double price;
                String username, pizzaName, ingredients, orderDate;
                while(res.next()){  //Iterate until insert all pizzas in 'list'
                    orderID = res.getInt("orderID");
                    username = res.getString("username");
                    pizzaName = res.getString("name");
                    ingredients = res.getString("ingredients");
                    quantity = res.getInt("quantity");
                    price = res.getDouble("price");
                    orderDate = res.getString("orderDate");
                    
                    list.add(new AdminOrders(orderID,username,pizzaName,
                    ingredients, quantity, price,orderDate));
                }
                return list;
            }
            catch(Exception e){
                return null;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method used to insert a new pizza in the DB
         * @param name should be a non empty String
         * @param price should be a non empty String
         * @param ingredients should be a non empty String
         * @return an integer value used to show feedback to the user.
         * @throws Exception (SQLException)
         */
        public int insertPizza(String name, String price, String ingredients) throws Exception{
            Connection mysql = null;
            int val = -1;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement();
                String query = "INSERT INTO pizza(name,price,ingredients) "
                        + "VALUES ('" + name + "'," + price + ",'" +
                        ingredients + "')";
                val = st.executeUpdate(query);
                return val;
            }
            catch(Exception e){
                return val;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method used to delete existing pizzas (Only admin user)
         * @param pizzaID should be a not empty String
         * @return an integer value used to show feedback to the user.
         * @throws Exception (SQLException)
         */
        public int deletePizza(String pizzaID) throws Exception{
            Connection mysql = null;
            int val = -1;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement();
                String query = "DELETE FROM pizza WHERE idPizza = " + pizzaID;
                val = st.executeUpdate(query);
                return val;
            }
            catch(Exception e){
                return val;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method used to change the values of an inserted pizza
         * @param pizzaID should be a existing pizzaID in the DB.
         * @param price should be a non empty String with the new price
         * @param ingredients should be a non empty String
         * @return an integer value used to show feedback to the user
         * @throws Exception (SQLException)
         */
        public int modifyPizza(String pizzaID, String price, String ingredients) throws Exception{
            Connection mysql = null;
            int val = -1;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement();
                String query = "UPDATE pizza SET price = " + price + ","
                        + "ingredients = '" + ingredients +
                        "' WHERE idPizza = " + pizzaID;
                val = st.executeUpdate(query);
                return val;
            }
            catch(Exception e){
                return val;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
        
        /**
         * Method used to delete an existing order of a user
         * @param orderID should be an existing orderID
         * @param username should be an existing username in the DB
         * @return an integer value used to show feedback to the user.
         * @throws Exception (SQLException)
         */
        public int deleteOrder(String orderID, String username) throws Exception{
            Connection mysql = null;
            int val = -1;
            try{
                DBConnection db = new DBConnection();
		mysql = db.startConnection();
		Statement st = mysql.createStatement();
                String query = "DELETE FROM pizzaOrder WHERE orderID = " + 
                        orderID + " AND username = '" + username + "'";
                val = st.executeUpdate(query);
                System.err.println("DELETED ROWS: " + val);
                return val;
            }
            catch(Exception e){
                return val;
            }
            finally{
                if(mysql != null){
                    mysql.close();
                }
            }
        }
}
