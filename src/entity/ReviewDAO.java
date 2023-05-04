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
public class ReviewDAO implements DAO<Review>
{   
    public ReviewDAO() {
        
    }
    List<Review> reviews;
    /**
     * Get a single review entity as an review object
     * @param id
     * @return 
     */
    @Override
    public Optional<Review> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Review WHERE ReviewID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Review review = null;
            while (rs.next()) {
                review = new Review(rs.getInt("ReviewID"), rs.getInt("UserID"), rs.getInt("ProductID"), rs.getInt("Rating"), rs.getString("Comment"));
            }
            return Optional.ofNullable(review);
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
    public List<Review> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        reviews = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Review";
            rs = db.executeQuery(sql);
            Review review = null;
            while (rs.next()) {
                review = new Review(rs.getInt("ReviewID"), rs.getInt("UserID"), rs.getInt("ProductID"), rs.getInt("Rating"), rs.getString("Comment"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert an review object into review table
     * @param review 
     */
    @Override
    public void insert(Review review)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Review(ReviewID, UserID, ProductID, Rating, Comment) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, review.getReviewID());
            stmt.setInt(2, review.getReviewUserID());
            stmt.setInt(3, review.getReviewProductID());
            stmt.setInt(4, review.getRating());
            stmt.setString(5, review.getComment());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new review was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update an review entity in database if it exists using an review object
     * @param review
     */
    @Override
    public void update(Review review) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Review SET UserID=?, ProductID=?, Rating=?, Comment=? WHERE ReviewID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(5, review.getReviewID());
            stmt.setInt(1, review.getReviewUserID());
            stmt.setInt(2, review.getReviewProductID());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing review was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete an review from review table if the entity exists
     * @param review 
     */
    @Override
    public void delete(Review review) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Review WHERE ReviewID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, review.getReviewID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An review was deleted successfully!");
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
            String sql = "SELECT * FROM Review WHERE ReviewID = -1";//We just need this sql query to get the column headers
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
