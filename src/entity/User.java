/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Steven
 */
public class User 
{
    private int UserId;
    private String FirstName;
    private String LastName;
    
    
    public User(int UserId, String FirstName, String LastName)
    {
        this.UserId = UserId;
        this.FirstName = FirstName;
        this.LastName = LastName;
        
    }

    public int getUserID() {
        return UserId;
    }

    public String getFirstName() {
        return FirstName;
    }
    
     public String getLastName() {
        return LastName;
    }

 

    @Override
    public String toString() {
        return "UserID{" + "ID=" + UserId + ", firstName=" + FirstName + ",lastName=" + LastName + '}';
    }
}
