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
public class Product 
{
    private int ProductID;
    private String ProductType;
    private String Description;
    private String ProductName;
    private String Price;
    
    
    
    public Product(int ProductID, String ProductType,String ProductName, String Price, String Description)
    {
        this.ProductID = ProductID;
        this.ProductType = ProductType;
        this.Description = Description;
        this.ProductName = ProductName;
        this.Price = Price;
        
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductType() {
        return ProductType;
    }

     public String getProductName() {
        return ProductName;
    }
     public String getPrice() {
        return Price;
    }
     public String getDescription() {
        return Description;
    }

 

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductType=" + ProductType + ", ProductName=" + ProductName + ", Price=" + Price + ", Description=" + Description + '}';
    }
}
