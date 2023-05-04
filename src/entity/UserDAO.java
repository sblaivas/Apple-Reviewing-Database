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
public class UserDAO implements DAO<User>
{   
    public UserDAO() {
        
    }
    List<User> users;
    /**
     * Get a single UserDAO location entity as a UserDAO object
     * @param id
     * @return 
     */
    @Override
    public Optional<User> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM A_User WHERE UserId = ?";
            
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getInt("UserId"), rs.getString("FirstName"),rs.getString("LastName"));
            }
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all UserDAO entities as a List
     * @return 
     */
    @Override
    public List<User> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM A_User";
            rs = db.executeQuery(sql);
            User user = null;
            while (rs.next()) {
                user = new User(rs.getInt("UserId"), rs.getString("FirstName"),rs.getString("LastName"));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a UserDAO object into UserDAO table
     * @param user 
     */
    @Override
    public void insert(User user)
    { 
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO A_User(UserId, FirstName, LastName) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());

            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a UserDAO entity in database if it exists using a UserDAO object
     * @param user
     */
    @Override
    public void update(User user) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE A_User SET FirstName=?,LastName=? WHERE UserId = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(3, user.getUserID());
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            
            
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a UserDAO from UserDAO table if the entity exists
     * @param user 
     */
    @Override
    public void delete(User user) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM A_User WHERE UserId = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, user.getUserID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
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
            String sql = "SELECT * FROM A_User WHERE UserId = -1";//We just need this sql query to get the column headers
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
