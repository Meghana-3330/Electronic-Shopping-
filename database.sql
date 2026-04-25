CREATE DATABASE IF NOT EXISTS shoppingcart;
USE shoppingcart;

CREATE TABLE IF NOT EXISTS user (
    email VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    mobile VARCHAR(15),
    address VARCHAR(255),
    pincode VARCHAR(10),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
    pid INT AUTO_INCREMENT PRIMARY KEY,
    pname VARCHAR(255),
    type VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT,
    image VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usercart (
    username VARCHAR(255),
    prodid INT,
    quantity INT,
    FOREIGN KEY (username) REFERENCES user(email),
    FOREIGN KEY (prodid) REFERENCES product(pid)
);

CREATE TABLE IF NOT EXISTS orders (
    orderid VARCHAR(50) PRIMARY KEY,
    prodid INT,
    username VARCHAR(255),
    quantity INT,
    amount DECIMAL(10, 2),
    shipped BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (prodid) REFERENCES product(pid),
    FOREIGN KEY (username) REFERENCES user(email)
);

CREATE TABLE IF NOT EXISTS transactions (
    txnid VARCHAR(50) PRIMARY KEY,
    username VARCHAR(255),
    time DATETIME,
    amount DECIMAL(10, 2),
    FOREIGN KEY (username) REFERENCES user(email)
);

-- Insert Dummy Data for Products
INSERT INTO product (pname, type, price, quantity, image) VALUES 
('iPhone 14 Pro', 'Mobiles', 120000.00, 10, 'iphone14.jpg'),
('Samsung Galaxy S23', 'Mobiles', 95000.00, 15, 's23.jpg'),
('Sony Bravia 55 inch 4K', 'TV', 75000.00, 5, 'sonytv.jpg'),
('MacBook Pro M2', 'Laptops', 150000.00, 8, 'macbook.jpg'),
('Dell XPS 13', 'Laptops', 110000.00, 12, 'dellxps.jpg'),
('Canon EOS R5', 'Camera', 250000.00, 3, 'canon.jpg'),
('JBL Flip 6', 'Speakers', 10000.00, 20, 'jbl.jpg'),
('iPad Air 5', 'Tablets', 55000.00, 10, 'ipad.jpg'),
('Floral Summer Dress', 'Dresses', 2500.00, 30, 'dress_summer.jpg'),
('Elegant Evening Gown', 'Dresses', 8500.00, 10, 'dress_evening.jpg'),
('Casual T-Shirt Dress', 'Dresses', 1500.00, 50, 'dress_casual.jpg');

-- Insert Dummy Admin User
INSERT INTO user (email, name, mobile, address, pincode, password) VALUES
('admin@ellison.com', 'Admin User', '9999999999', 'Admin Office', '100001', 'admin123');
