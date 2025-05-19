DROP DATABASE IF EXISTS RETAILSTORE;
CREATE DATABASE RETAILSTORE; 
USE RETAILSTORE;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE Customer (
  CustomerID int(11) NOT NULL,
  CustomerName varchar(100) NOT NULL,
  HOUSENUMBER varchar(50) NOT NULL,
  City varchar(50) NOT NULL,
  State varchar(50) NOT NULL,
  Pincode varchar(10) NOT NULL,
  EmailID varchar(100) NOT NULL,
  PhoneNumber varchar(10) NOT NULL,
  Password VARCHAR(50) NOT NULL,
  PRIMARY KEY (CustomerID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO Customer (CustomerID, CustomerName, HOUSENUMBER, City, State, Pincode, EmailID, PhoneNumber,Password) VALUES
(1, 'KATE', 'IIITD H-1', 'NEW DELHI', 'NEW DELHI', '100020', 'kate23@gmail.com', '8529411200',"password1"),
(2, 'JIMMY', 'IIITD H-2', 'NEW DELHI', 'NEW DELHI', '100020', 'jimmy69@gmail.com', '9414852655',"password2"),
(3, 'NAMAN', 'IIITD OLD BOYS HOSTEL', 'NEW DELHI', 'NEW DELHI', '100020', 'naman420@gmail.com', '7976478985',"password3"),
(4, 'Alice Johnson', '789 Oak St', 'Chicago', 'IL', '60601', 'alice@example.com', '5551234567',"password4"),
(5, 'Jane Smith', '456 Elm St', 'Los Angeles', 'CA', '90001', 'jane@example.com', '9876543210',"password5"),
(6, 'David Lee', '888 Cherry Ln', 'Boston', 'MA', '02101', 'david@example.com', '4445556666',"password6"),
(7, 'Emily Brown', '555 Maple Ave', 'Seattle', 'WA', '98101', 'emily@example.com', '9998887777',"password7"),
(8, 'Priya Patel', '303 Gandhi Nagar', 'Mumbai', 'Maharashtra', '400001', 'priya@example.com', '1234567890',"password8"),
(9, 'Amit Singh', '505 Jai Nagar', 'Bangalore', 'Karnataka', '560001', 'amit@example.com', '999999999',"password9"),
(10, 'Rahul Sharma', '101 Nehru Road', 'Delhi', 'Delhi', '110001', 'rahul@example.com', '9876543210',"password10"),
(11, 'Sara Khan', '123 Pine St', 'San Francisco', 'CA', '94101', 'sara@example.com', '1112223333',"password11"),
(12, 'Michael Brown', '456 Oak St', 'New York', 'NY', '10001', 'michael@example.com', '4445556666',"password12"),
(13, 'Sophia Patel', '789 Elm St', 'Chicago', 'IL', '60601', 'sophia@example.com', '7778889999',"password13"),
(14, 'Alexander Wang', '101 Maple Ave', 'Seattle', 'WA', '98101', 'alexander@example.com', '8887776666',"password14"),
(15, 'Olivia Kim', '303 Cherry Ln', 'Los Angeles', 'CA', '90001', 'olivia@example.com', '3332221111',"password15"),
(16, 'Ethan Garcia', '505 Pine St', 'Boston', 'MA', '02101', 'ethan@example.com', '6667778888',"password16"),
(17, 'Ava Rodriguez', '707 Oak St', 'San Francisco', 'CA', '94101', 'ava@example.com', '9998887777',"password17"),
(18, 'Noah Martinez', '909 Elm St', 'New York', 'NY', '10001', 'noah@example.com', '2223334444',"password18"),
(19, 'Isabella Lopez', '111 Maple Ave', 'Chicago', 'IL', '60601', 'isabella@example.com', '5554443333',"password19"),
(20, 'William Hernandez', '222 Cherry Ln', 'Seattle', 'WA', '98101', 'william@example.com', '8889990000',"password20"),
(21, 'Mia Gonzalez', '333 Pine St', 'Los Angeles', 'CA', '90001', 'mia@example.com', '1112223333',"password21"),
(22, 'James Taylor', '444 Oak St', 'Boston', 'MA', '02101', 'james@example.com', '4445556666',"password22"),
(23, 'Charlotte Davis', '555 Elm St', 'San Francisco', 'CA', '94101', 'charlotte@example.com', '7777777777',"password23"),
(24, 'John Smith', '777 Maple Ave', 'Seattle', 'WA', '98101', 'john@example.com', '6666666666',"password24"),
(25, 'Emma Johnson', '888 Pine St', 'Los Angeles', 'CA', '90001', 'emma@example.com', '5555555555',"password25"),
(26, 'Daniel Garcia', '999 Oak St', 'Boston', 'MA', '02101', 'daniel@example.com', '4444444444',"password26"),
(27, 'Sophia Hernandez', '111 Elm St', 'San Francisco', 'CA', '94101', 'sophia2@example.com', '3333333333',"password27"),
(28, 'Liam Martinez', '222 Maple Ave', 'Chicago', 'IL', '60601', 'liam@example.com', '2222222222',"password28"),
(29, 'Olivia Brown', '333 Cherry Ln', 'Seattle', 'WA', '98101', 'olivia2@example.com', '1111111111',"password29"),
(30, 'Ethan Wilson', '444 Pine St', 'Los Angeles', 'CA', '90001', 'ethan2@example.com', '9999999999',"password30");




CREATE TABLE Items (
  ProductID int(11) NOT NULL,
  itemName varchar(100) NOT NULL,
  QUANTITY int(11) NOT NULL,
  itemPrice decimal(9,2) NOT NULL,
  itemDescription varchar(200) NOT NULL,
  Reviews text ,
  Ratings DECIMAL(2,1),
  PRIMARY KEY (ProductID)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO Items VALUES (1,'Foam Dinner Plate',70,100.00,'Foam Dinner Plate 12inch white','terrific',4.6);
INSERT INTO Items VALUES (2,'chicken,back Peameal',49,100.00,'fresh 500gm','great',4.2);
INSERT INTO Items VALUES (3,'Lettuce - Romaine, Heart',38,100.00,'Fresh crunchu 250gm','appalling',1.8);
INSERT INTO Items VALUES (4,'Brocolinni - Gaylan',90,100.00,'chinese 500gm','good',3.6);
INSERT INTO Items VALUES (5,'Sauce - Ranch Dressing',94,100.00,'300ml Ranch fresh mint','terrific',4.8);
INSERT INTO Items VALUES (6,'Petit Baguette',14,100.00,'12inch baguette salted','terrific',4.2);
INSERT INTO Items VALUES (7,'Sweet Pea Sprouts',98,100.00,'300gm sweet pea light green','terrific',4.8);
INSERT INTO Items VALUES (8,'Island Oasis - Raspberry',26,100.00,'freshly imported Raspberry 500gm','good',3.6);
INSERT INTO Items VALUES (9,'ruler',67,100.00,'15 cm plastic ruler','good',3.4);
INSERT INTO Items VALUES (10,'Broom - Push',6,100.00,'straw broom','terrific',4.9);


CREATE TABLE DeliveryPartner (
  DeliverypartnerID INT NOT NULL,
  Name varchar(50) NOT NULL,
  emailID varchar(100) NOT NULL,
  PRIMARY KEY (DeliveryPartnerID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO DeliveryPartner VALUES (1,'KAGUYA','kaguya@gmail.com');
INSERT INTO DeliveryPartner VALUES (2,'LIGHT','light@gmail.com');
INSERT INTO DeliveryPartner VALUES (3,'SHIROGANE','shirogane@gmail.com');
INSERT INTO DeliveryPartner VALUES (4,'JOTARO','jotaro@gmail.com');
INSERT INTO DeliveryPartner VALUES (5,'WALTER WHITE','walter@gmail.com');
INSERT INTO DeliveryPartner VALUES (6,'parlie WHITE','parlie@gmail.com');
INSERT INTO DeliveryPartner VALUES (7,'JESSIE','jessie@gmail.com');
INSERT INTO DeliveryPartner VALUES (8,'SKYLAR','skylar@gmail.com');
INSERT INTO DeliveryPartner VALUES (9,'SAUL','saul@gmail.com');
INSERT INTO DeliveryPartner VALUES (10,'FRINGE','fringe@gmail.com');

CREATE TABLE Orders (
  CustomerID int(11) NOT NULL,
  OrderID int(11) NOT NULL ,
  OrderDate date NOT NULL,
  Quantity int(11) NOT NULL,
  CurrentStatus tinyint(4) NOT NULL DEFAULT '1',
  DeliveryDate date DEFAULT NULL,
  DeliveryPartnerID INT DEFAULT NULL,
  PRIMARY KEY (OrderID),
  KEY foreignkey_orders_deliverypartner (DeliveryPartnerID),
  KEY foreignkey_OrderCustomer (CustomerID),
  CONSTRAINT foreignkey_OrderCustomer FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ON UPDATE CASCADE,
  CONSTRAINT foreignkey_orders_deliverypartner FOREIGN KEY (DeliveryPartnerID) REFERENCES DeliveryPartner (DeliveryPartnerID) ON UPDATE CASCADE
 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO Orders VALUES (1,6,'2023-01-25',1,1,NULL,NULL);
INSERT INTO Orders VALUES (2,7,'2023-08-06',2,1,'2018-08-03',4);
INSERT INTO Orders VALUES (3,8,'2023-12-24',1,1,NULL,NULL);
INSERT INTO Orders VALUES (4,2,'2024-01-21',1,1,NULL,NULL);
INSERT INTO Orders VALUES (5,1,'2023-08-05',2,1,'2017-08-26',3);
INSERT INTO Orders VALUES (6,10,'2023-11-28',1,1,NULL,5);
INSERT INTO Orders VALUES (7,3,'2023-09-21',2,1,'2018-09-23',7);
INSERT INTO Orders VALUES (8,5,'2023-06-09',1,1,NULL,NULL);
INSERT INTO Orders VALUES (9,4,'2023-07-15',2,1,'2017-07-06',1);
INSERT INTO Orders VALUES (10,9,'2023-04-12',2,1,'2018-04-23',2);
INSERT INTO Orders (CustomerID, OrderID, OrderDate, Quantity, CurrentStatus, DeliveryDate, DeliveryPartnerID)
VALUES
(1, 21, '2024-03-01', 3, 1, NULL, 5),
(1, 22, '2024-03-15', 2, 1, NULL, NULL),
(2, 23, '2024-03-10', 2, 1, NULL, 3),
(3, 24, '2024-03-05', 1, 1, NULL, NULL),
(4, 25, '2024-03-20', 3, 1, NULL, 2),
(5, 26, '2024-03-25', 1, 1, NULL, NULL);




INSERT INTO Orders (CustomerID, OrderID, OrderDate, Quantity, CurrentStatus, DeliveryDate, DeliveryPartnerID)
VALUES
(1, 15, '2024-03-01', 3, 1, NULL, 5),
(1, 16, '2024-03-15', 2, 1, NULL, NULL),
(2, 17, '2024-03-10', 2, 1, NULL, 3),
(3, 18, '2024-03-05', 1, 1, NULL, NULL),
(4, 19, '2024-03-20', 3, 1, NULL, 2),
(5, 20, '2024-03-25', 1, 1, NULL, NULL);




CREATE TABLE Cart (
  CustomerID int(11) NOT NULL,
  ProductID int(11) NOT NULL,
  quantity int(11) NOT NULL,
  ApplyDiscount boolean,
  TotalSum decimal(9,2) NOT NULL,
  DeliveryInstruction TEXT,
  PRIMARY KEY (CustomerID,ProductID),
  KEY fk_cart_customer (CustomerID),
  KEY fk_order_items_products_idx (ProductID),
  CONSTRAINT fk_cart_customer FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ON UPDATE CASCADE,
  CONSTRAINT fk_order_items_products FOREIGN KEY (ProductID) REFERENCES Items (ProductID) ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO Cart (CustomerID, ProductID, quantity, ApplyDiscount, TotalSum, DeliveryInstruction)
VALUES
(1, 1, 2, 1, 200.00, 'Handle with care'),
(2, 2, 1, 0, 100.00, 'Fragile, do not shake'),
(3, 3, 3, 1, 300.00, 'Deliver before 5 PM'),
(4, 4, 2, 0, 200.00, 'Leave at the doorstep'),
(5, 5, 1, 1, 100.00, 'Call upon arrival'),
(6, 6, 4, 1, 400.00, 'Gift wrap required'),
(7, 7, 2, 0, 200.00, 'Sign upon delivery'),
(8, 8, 1, 1, 100.00, 'No delivery on Sundays'),
(9, 9, 3, 0, 300.00, 'Special instructions: None'),
(10, 10, 2, 1, 200.00, 'Delivery to the back entrance');



CREATE INDEX idx_customer_city ON Customer(City);
CREATE INDEX idx_customer_pin ON Customer(Pincode);
CREATE INDEX idx_customer_email ON Customer(EmailID);
CREATE INDEX idx_customer_phone ON Customer(PhoneNumber);



-- Create DeliveryInfo table
CREATE TABLE DeliveryInfo (
  DeliveryID INT PRIMARY KEY,
  CustomerName VARCHAR(100) NOT NULL,
  CustomerCity VARCHAR(50) NOT NULL,
  CustomerState VARCHAR(50) NOT NULL,
  CustomerPincode VARCHAR(10) NOT NULL,
  CustomerEmailID VARCHAR(100) NOT NULL,
  CustomerPhoneNumber VARCHAR(10) ,
  TotalSum DECIMAL(10, 2),
  OrderID INT,
  DeliveryPartnerID INT,
  
  CONSTRAINT fk_deliveryinfo_order FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE,
  CONSTRAINT fk_deliveryinfo_deliverypartner FOREIGN KEY (DeliveryPartnerID) REFERENCES DeliveryPartner(DeliveryPartnerID) ON UPDATE CASCADE,
  CONSTRAINT fk_deliveryinfo_city FOREIGN KEY (CustomerCity) REFERENCES Customer(City) ON UPDATE CASCADE,
  CONSTRAINT fk_deliveryinfo_pin FOREIGN KEY (CustomerPincode) REFERENCES Customer(Pincode) ON UPDATE CASCADE,
  CONSTRAINT fk_deliveryinfo_email FOREIGN KEY (CustomerEmailID) REFERENCES Customer(EmailID) ON UPDATE CASCADE,
  CONSTRAINT fk_deliveryinfo_phone FOREIGN KEY (CustomerPhoneNumber) REFERENCES Customer(PhoneNumber) ON UPDATE CASCADE
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO DeliveryInfo (DeliveryID, CustomerName, CustomerCity, CustomerState, CustomerPincode, CustomerEmailID, CustomerPhoneNumber, TotalSum, OrderID, DeliveryPartnerID)
VALUES
(1, 'KATE', 'NEW DELHI', 'NEW DELHI', '100020', 'kate23@gmail.com', '8529411200', 200.00, 6, 1),
(2, 'JIMMY', 'NEW DELHI', 'NEW DELHI', '100020', 'jimmy69@gmail.com', '9414852655', 100.00, 7, 2),
(3, 'NAMAN', 'NEW DELHI', 'NEW DELHI', '100020', 'naman420@gmail.com', '7976478985', 300.00, 8, 3),
(4, 'Alice Johnson', 'Chicago', 'IL', '60601', 'alice@example.com', '5551234567',200.00, 2, 4),
(5, 'Jane Smith', 'Los Angeles', 'CA', '90001', 'jane@example.com', '9876543210', 100.00, 5, 5),
(6, 'David Lee', 'Boston', 'MA', '02101', 'david@example.com', '4445556666', 400.00, 10, 5),
(7, 'Emily Brown', 'Seattle', 'WA', '98101', 'emily@example.com', '9998887777', 200.00, 2, 7),
(8, 'Priya Patel', 'Mumbai', 'Maharashtra', '400001', 'priya@example.com', '1234567890', 100.00, 5, 8),
(9, 'Amit Singh', 'Bangalore', 'Karnataka', '560001', 'amit@example.com', '999999999', 300.00, 10, 1),
(10, 'Rahul Sharma', 'Delhi', 'Delhi', '110001', 'rahul@example.com', '9876543210',  200.00, 6, 2);

CREATE TABLE Payment 
(
  PaymentID int(11) NOT NULL ,
  Amount decimal(9,2) NOT NULL,
  PaymentMethod varchar(50) NOT NULL,
  payment_date date NOT NULL,
  OrderID int(11) NOT NULL,
  PRIMARY KEY (PaymentID),
  CONSTRAINT foreignkey_paymentOrderID FOREIGN KEY (OrderID) REFERENCES Orders (OrderID) ON UPDATE CASCADE
  )
  ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO Payment VALUES (1,100.00,'method 2','2019-02-12',1);
INSERT INTO Payment VALUES (2,100.00,'method 6','2019-01-03',1);
INSERT INTO Payment VALUES (3,100.00,'method 3','2019-01-11',1);
INSERT INTO Payment VALUES (4,100.00,'method 4','2019-01-26',1);
INSERT INTO Payment VALUES (5,100.00,'method 5','2019-01-15',1);
INSERT INTO Payment VALUES (6,100.00,'method 7','2019-01-15',1);
INSERT INTO Payment VALUES (7,100.00,'method 8','2019-01-08',1);
INSERT INTO Payment VALUES (8,100.00,'method 8','2019-01-08',2);
INSERT INTO Payment VALUES (9,100.00,'method 8','2019-01-08',2);
INSERT INTO Payment VALUES (10,100.00,'method 1','2019-01-08',2);

CREATE TABLE Deals (
    DealCode VARCHAR(20) PRIMARY KEY,
    Discount DECIMAL(5, 2),
    Descriptions TEXT
);
INSERT INTO Deals (DealCode, Discount, Descriptions) VALUES 
('DEAL1', 10.00, '10% discount on selected items'),
('DEAL2', 15.00, '15% discount on first purchase'),
('DEAL3', 20.00, '20% discount on items'),
('DEAL4', 5.00, 'Additional $5 off on items'),
('DEAL5', 12.50, '12.5% discount on item'),
('DEAL6', 25.00, '25% discount on electronics'),
('DEAL7', 30.00, '30% discount on home appliances'),
('DEAL8', 8.00, '$8 off on orders on item'),
('DEAL9', 18.00, '18% discount on clothing items'),
('DEAL10', 7.50, '7.5% discount on beauty products');


CREATE TABLE ItemsDeals (
    ProductID INT,
    DealCode VARCHAR(20),
    PRIMARY KEY (ProductID, DealCode),
    FOREIGN KEY (ProductID) REFERENCES Items(ProductID),
    FOREIGN KEY (DealCode) REFERENCES Deals(DealCode)
);
INSERT INTO ItemsDeals (ProductID, DealCode) VALUES 
(1, 'DEAL1'),
(2, 'DEAL2'),
(3, 'DEAL3'),
(4, 'DEAL4'),
(5, 'DEAL5'),
(6, 'DEAL6'),
(7, 'DEAL7'),
(8, 'DEAL8'),
(9, 'DEAL9'),
(10, 'DEAL10');

CREATE TABLE Supplier (
    SupplierID INT PRIMARY KEY,
    SupplierState VARCHAR(80),
    SupplierCity VARCHAR(80),
    SupplierHomeNumber VARCHAR(200),
    SupplierPincode VARCHAR(10),
    SupplierName VARCHAR(100),
    SupplierPhoneNumber VARCHAR(10)
    
);
INSERT INTO Supplier (SupplierID, SupplierState, SupplierCity, SupplierHomeNumber, SupplierPincode, SupplierName, SupplierPhoneNumber) VALUES 
(1, 'Delhi', 'New Delhi', 'ABC Apartment, Street 1', '110001', 'ABC Suppliers', '9876543210'),
(2, 'Maharashtra', 'Mumbai', 'XYZ Complex, Street 2', '400001', 'XYZ Enterprises', '1234567890'),
(3, 'Tamil Nadu', 'Chennai', 'DEF Towers, Street 3', '600001', 'DEF Traders', '9999999999'),
(4, 'Karnataka', 'Bangalore', 'PQR House, Street 4', '560001', 'PQR Distributors', '8888888888'),
(5, 'West Bengal', 'Kolkata', 'LMN Building, Street 5', '700001', 'LMN Suppliers', '7777777777'),
(6, 'Gujarat', 'Ahmedabad', 'JKL Mansion, Street 6', '380001', 'JKL Distributors', '6666666666'),
(7, 'Telangana', 'Hyderabad', 'EFG Plaza, Street 7', '500001', 'EFG Traders', '5555555555'),
(8, 'Punjab', 'Chandigarh', 'RST Complex, Street 8', '160001', 'RST Enterprises', '4444444444'),
(9, 'Uttar Pradesh', 'Lucknow', 'UVW House, Street 9', '226001', 'UVW Suppliers', '3333333333'),
(10, 'Rajasthan', 'Jaipur', 'MNO Tower, Street 10', '302001', 'MNO Distributors', '2222222222');

CREATE TABLE ItemsSuppliers (
    ProductID INT,
    SupplierID INT,
    PRIMARY KEY (ProductID, SupplierID),
    FOREIGN KEY (ProductID) REFERENCES Items(ProductID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
);
INSERT INTO ItemsSuppliers (ProductID, SupplierID) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

CREATE TABLE Admin (
    AdminID INT PRIMARY KEY,
    AdminName VARCHAR(100) NOT NULL,
    UserEmail VARCHAR(50) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(10) NOT NULL, 
    Password VARCHAR(20) NOT NULL
);
INSERT INTO Admin(AdminID,AdminName,UserEmail,PhoneNumber,Password) Values
(1,'Harsh Vishwakarma','harsh123@gmail.com','8529411299',"Harsh30609"),
(2,"Kartik","kartik23@gmail.com","2356234523","Kartik23"),
(3,'Lakshya Dayma','lakshya23@gmail.com','8529445299',"Lakshya2")
;

DELIMITER //

CREATE TRIGGER update_quantity_in_stock
AFTER INSERT ON Orders
FOR EACH ROW
BEGIN
    DECLARE ordered_quantity INT;
    
    -- Get the ordered quantity from the Orders table
    SELECT Quantity INTO ordered_quantity
    FROM Orders
    WHERE OrderID = NEW.OrderID;
    
    -- Update the quantity in stock in the Items table
    UPDATE Items
    SET QUANTITY = QUANTITY - ordered_quantity
    WHERE ProductID IN (
        SELECT ProductID
        FROM Cart
        WHERE CustomerID = NEW.CustomerID
    );
END //

DELIMITER ;

CREATE TABLE LoginAttempts (
    CustomerID INT NOT NULL,
    Attempts INT DEFAULT 0,
    LastAttempt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Blocked BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (CustomerID)
);
DELIMITER //

CREATE TRIGGER Account_Block_after_3_attempts AFTER INSERT ON loginattempts
FOR EACH ROW
BEGIN
    DECLARE attempts INT;
    DECLARE blocked BOOLEAN;
    
    -- Get the current number of attempts and block status
    SELECT Attempts, Blocked INTO attempts, blocked
    FROM LoginAttempts
    WHERE CustomerID = NEW.CustomerID;
    
    -- Update the number of attempts and block status
    SET attempts = attempts + 1;
    IF attempts >= 3 THEN
        SET blocked = TRUE;
    END IF;
    
    -- Update the LoginAttempts table
    UPDATE LoginAttempts
    SET Attempts = attempts, Blocked = blocked, LastAttempt = CURRENT_TIMESTAMP
    WHERE CustomerID = NEW.CustomerID;
END //

DELIMITER ;


