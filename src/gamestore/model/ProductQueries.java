// The PreparedStatements that are used in the GameSpot application.
package gamestore.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductQueries {
    private static final String URL = "jdbc:mysql://localhost:3306/gamespot_db?zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "nbuser";
    
    private Connection connection; // manages the connection
    private PreparedStatement selectAllProducts;
    private PreparedStatement selectAlreadyPurchasedProducts;
    
    // Constructor
    public ProductQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            // creates the query for all the records in Product table
            selectAllProducts = connection.prepareStatement("SELECT * FROM products");
                        
            // creates the query for all purchased products
            selectAlreadyPurchasedProducts = connection.prepareStatement("SELECT ProductID FROM orders WHERE CustomerID = ?");
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public List<Product> getAllProducts() {
        List<Product> results = null;
        ResultSet resultSet = null;
        
        try {
        // executeQuery returns a ResultSet that contains the desired records
        resultSet = selectAllProducts.executeQuery();
        results = new ArrayList<Product>();
        while (resultSet.next()) {
            results.add(new Product(
                    resultSet.getInt("productID"),
                    resultSet.getString("title"),
                    resultSet.getDouble("price"),
                    resultSet.getString("description"),
                    resultSet.getString("video"),
                    resultSet.getString("platform"),
                    resultSet.getString("cover"),
                    resultSet.getString("image1"),
                    resultSet.getString("image2"),
                    resultSet.getString("image3"),
                    resultSet.getString("image4"),
                    resultSet.getString("exeFile")
            ));
        }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        
        return results;
    }
    
    public List<Integer> getAlreadyPurchasedProducts(int customerID) {
        List<Integer> results = null;
        ResultSet resultSet = null;
        
        try {
            selectAlreadyPurchasedProducts.setInt(1, customerID);
            
            // executeQuery returns a ResultSet with all the desired records
            resultSet = selectAlreadyPurchasedProducts.executeQuery();
            
            results = new ArrayList<Integer>();
            
            while(resultSet.next()) {
                int productID = resultSet.getInt("productID");
                results.add(productID);
            }
            
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }
        }
        
        return results;
    }
    
    // closes the connection with the database
    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}