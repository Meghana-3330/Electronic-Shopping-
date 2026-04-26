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
    paymentMethod VARCHAR(50),
    FOREIGN KEY (prodid) REFERENCES product(pid),
    FOREIGN KEY (username) REFERENCES user(email)
);

CREATE TABLE IF NOT EXISTS transactions (
    txnid VARCHAR(50) PRIMARY KEY,
    username VARCHAR(255),
    time DATETIME,
    amount DECIMAL(10, 2),
    paymentMethod VARCHAR(50),
    FOREIGN KEY (username) REFERENCES user(email)
);

-- Insert Dummy Data for Products
INSERT INTO product (pname, type, price, quantity, image) VALUES 
('iPhone 14 Pro', 'Mobiles', 120000.00, 10, 'iphone14.jpg'),
('Samsung Galaxy S23', 'Mobiles', 95000.00, 15, 's23.jpg'),
('OnePlus 11', 'Mobiles', 55000.00, 20, 'mobiles_1.jpg'),
('Google Pixel 7', 'Mobiles', 60000.00, 12, 'mobiles_2.jpg'),
('Xiaomi 13 Pro', 'Mobiles', 75000.00, 8, 'iphone14.jpg'),
('Vivo X90', 'Mobiles', 60000.00, 10, 's23.jpg'),
('Oppo Find X5', 'Mobiles', 65000.00, 5, 'mobiles_1.jpg'),
('Motorola Edge 40', 'Mobiles', 30000.00, 25, 'mobiles_2.jpg'),
('Realme GT 3', 'Mobiles', 45000.00, 18, 'iphone14.jpg'),
('Asus ROG Phone 7', 'Mobiles', 75000.00, 7, 's23.jpg'),
('Sony Bravia 55 inch 4K', 'TV', 75000.00, 5, 'sonytv.jpg'),
('Samsung 65 inch QLED', 'TV', 120000.00, 8, 'tv_1.jpg'),
('LG OLED 55 inch', 'TV', 110000.00, 6, 'tv_2.jpg'),
('OnePlus 50 inch 4K', 'TV', 40000.00, 15, 'sonytv.jpg'),
('TCL 55 inch Mini-LED', 'TV', 55000.00, 10, 'tv_1.jpg'),
('Hisense 65 inch ULED', 'TV', 65000.00, 12, 'tv_2.jpg'),
('Xiaomi 55 inch 4K', 'TV', 45000.00, 20, 'sonytv.jpg'),
('Vu 50 inch Premium', 'TV', 35000.00, 25, 'tv_1.jpg'),
('Acer 65 inch Advanced', 'TV', 50000.00, 8, 'tv_2.jpg'),
('Panasonic 55 inch Smart', 'TV', 60000.00, 10, 'sonytv.jpg'),
('MacBook Pro M2', 'Laptops', 150000.00, 8, 'macbook.jpg'),
('Dell XPS 13', 'Laptops', 110000.00, 12, 'dellxps.jpg'),
('Lenovo ThinkPad X1', 'Laptops', 130000.00, 6, 'laptops_1.jpg'),
('HP Spectre x360', 'Laptops', 120000.00, 10, 'laptops_2.jpg'),
('Asus ZenBook 14', 'Laptops', 85000.00, 15, 'macbook.jpg'),
('Acer Swift 5', 'Laptops', 75000.00, 18, 'dellxps.jpg'),
('Microsoft Surface Pro 9', 'Laptops', 105000.00, 9, 'laptops_1.jpg'),
('Razer Blade 15', 'Laptops', 180000.00, 5, 'laptops_2.jpg'),
('MSI Stealth 15M', 'Laptops', 140000.00, 7, 'macbook.jpg'),
('Samsung Galaxy Book3', 'Laptops', 115000.00, 11, 'dellxps.jpg'),
('Canon EOS R5', 'Camera', 250000.00, 3, 'canon.jpg'),
('Sony A7 IV', 'Camera', 210000.00, 5, 'camera_1.jpg'),
('Nikon Z7 II', 'Camera', 230000.00, 4, 'camera_2.jpg'),
('Fujifilm X-T5', 'Camera', 150000.00, 8, 'canon.jpg'),
('Panasonic Lumix GH6', 'Camera', 180000.00, 6, 'camera_1.jpg'),
('Olympus OM-1', 'Camera', 170000.00, 7, 'camera_2.jpg'),
('Sony A6700', 'Camera', 120000.00, 10, 'canon.jpg'),
('Canon EOS R6 Mark II', 'Camera', 200000.00, 4, 'camera_1.jpg'),
('Nikon Z50', 'Camera', 85000.00, 12, 'camera_2.jpg'),
('Leica Q2', 'Camera', 450000.00, 2, 'canon.jpg'),
('JBL Flip 6', 'Speakers', 10000.00, 20, 'jbl.jpg'),
('Bose SoundLink Flex', 'Speakers', 130000.00, 15, 'speakers_1.jpg'),
('Sony SRS-XB43', 'Speakers', 15000.00, 18, 'speakers_2.jpg'),
('Ultimate Ears Megaboom 3', 'Speakers', 18000.00, 10, 'jbl.jpg'),
('Marshall Emberton II', 'Speakers', 14000.00, 12, 'speakers_1.jpg'),
('Harman Kardon Aura Studio 3', 'Speakers', 25000.00, 8, 'speakers_2.jpg'),
('Sonos Roam', 'Speakers', 16000.00, 14, 'jbl.jpg'),
('Bang & Olufsen Beosound Explore', 'Speakers', 20000.00, 7, 'speakers_1.jpg'),
('Bowers & Wilkins Zeppelin', 'Speakers', 70000.00, 4, 'speakers_2.jpg'),
('Devialet Phantom II', 'Speakers', 120000.00, 3, 'jbl.jpg'),
('iPad Air 5', 'Tablets', 55000.00, 10, 'ipad.jpg'),
('Samsung Galaxy Tab S8', 'Tablets', 65000.00, 12, 'tablets_1.jpg'),
('Lenovo Tab P11 Pro', 'Tablets', 40000.00, 15, 'tablets_2.jpg'),
('iPad Pro 11-inch', 'Tablets', 80000.00, 8, 'ipad.jpg'),
('Xiaomi Pad 6', 'Tablets', 28000.00, 20, 'tablets_1.jpg'),
('Realme Pad X', 'Tablets', 20000.00, 25, 'tablets_2.jpg'),
('OnePlus Pad', 'Tablets', 38000.00, 18, 'ipad.jpg'),
('Microsoft Surface Go 3', 'Tablets', 45000.00, 12, 'tablets_1.jpg'),
('Asus ROG Flow Z13', 'Tablets', 150000.00, 5, 'tablets_2.jpg'),
('Amazon Fire HD 10', 'Tablets', 15000.00, 30, 'ipad.jpg');

-- Insert Dummy Admin User
INSERT INTO user (email, name, mobile, address, pincode, password) VALUES
('admin@ellison.com', 'Admin User', '9999999999', 'Admin Office', '100001', 'admin123');
