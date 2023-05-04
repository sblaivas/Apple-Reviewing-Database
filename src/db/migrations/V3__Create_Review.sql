CREATE TABLE Review (
    ReviewID int NOT NULL PRIMARY KEY,
    UserID int NOT NULL,
    ProductID int NOT NULL,
    Rating int NOT NULL,
    Comment VARCHAR(50) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES A_User(UserID)  ON DELETE RESTRICT,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE RESTRICT
    
   
);