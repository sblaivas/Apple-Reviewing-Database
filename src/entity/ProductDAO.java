/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Gokhan
 */
public class ProductDAO implements DAO<Product>
{   
    public ProductDAO() {
        
    }
    List<Product> products;
    /**
     * Get a single review entity as an review object
     * @param id
     * @return 
     */
    @Override
    public Optional<Product> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Product WHERE ProductID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt("ProductID"), rs.getString("ProductType"),rs.getString("ProductName"), rs.getString("Price"),rs.getString("Description"));
            }
            return Optional.ofNullable(product);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all review entities as a List
     * @return 
     */
    @Override
    public List<Product> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product";
            rs = db.executeQuery(sql);
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt("ProductID"), rs.getString("ProductType"),rs.getString("ProductName"), rs.getString("Price"),rs.getString("Description"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert an review object into review table
     * @param product 
     */
    @Override
    public void insert(Product product)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Product(ProductID, ProductType, ProductName, Price, Description) VALUES (?, ?, ? ,? ,? )";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getProductType());
            stmt.setString(3, product.getProductName());
            stmt.setString(4, product.getPrice());
            stmt.setString(5, product.getDescription());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new product was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update an review entity in database if it exists using an review object
     * @param product
     */
    @Override
    public void update(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Product SET ProductType=?, ProductName=?, Price=?, Description=? WHERE ProductID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(5, product.getProductID());
            stmt.setString(1, product.getProductType());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getPrice());
            stmt.setString(4, product.getDescription());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing product was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete an review from review table if the entity exists
     * @param product 
     */
    @Override
    public void delete(Product product) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Product WHERE ProductID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, product.getProductID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An product was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Product WHERE ProductID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
