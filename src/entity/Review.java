/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Gokhan
 */
public class Review 
{
    private int ReviewID;
    private int UserID;
    private int ProductID;
    private int Rating;
    private String Comment;
    
    public Review(int ReviewID, int UserID, int ProductID, int Rating, String Comment)
    {
        this.ReviewID = ReviewID;
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.Rating = Rating;
        this.Comment = Comment;
    }

    public int getReviewID() {
        return ReviewID;
    }

    public int getReviewUserID() {
        return UserID;
    }

    public int getReviewProductID() {
        return ProductID;
    }

    public int getRating() {
        return Rating;
    }
    
    public String getComment() {
        return Comment;
    }

    @Override
    public String toString() {
        return "Passenger{" + "ReviewID=" + ReviewID + ", UserID=" + UserID + ", ProductID=" + ProductID + ", Rating=" + Rating + ", Comment=" + Comment + '}';
    }
}
