
DROP DATABASE IF EXISTS sale_management;

CREATE DATABASE sale_management;

USE sale_management;
CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description VARCHAR(255)
);
CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(12,2) NOT NULL,
                          quantity INT NOT NULL,
                          image VARCHAR(255),
                          category_id BIGINT,

                          CONSTRAINT fk_product_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id)
);
CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE,
                           phone VARCHAR(20),
                           address VARCHAR(255)
);
CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        order_date DATETIME,
                        total_amount DECIMAL(12,2),

                        customer_id BIGINT,

                        CONSTRAINT fk_order_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
);
CREATE TABLE order_details (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,

                               order_id BIGINT,
                               product_id BIGINT,

                               quantity INT,
                               price DECIMAL(12,2),

                               CONSTRAINT fk_detail_order
                                   FOREIGN KEY (order_id)
                                       REFERENCES orders(id),

                               CONSTRAINT fk_detail_product
                                   FOREIGN KEY (product_id)
                                       REFERENCES products(id)
);
-- dữ liệu mẫu
INSERT INTO categories (name, description)
VALUES
    ('Điện thoại', 'Các loại điện thoại'),
    ('Laptop', 'Laptop Gaming'),
    ('Máy tính bảng', 'Tablet'),
    ('Phụ kiện', 'Tai nghe, Chuột, Bàn phím');
INSERT INTO products
(name, description, price, quantity, image, category_id)
VALUES
    ('iPhone 16 Pro',
     'Apple',
     32990000,
     20,
     'iphone16.jpg',
     1),

    ('Samsung S25 Ultra',
     'Samsung',
     28990000,
     15,
     's25.jpg',
     1),

    ('MacBook Pro M4',
     'Apple Laptop',
     55990000,
     10,
     'macbook.jpg',
     2),

    ('Asus ROG Strix',
     'Gaming Laptop',
     38990000,
     12,
     'rog.jpg',
     2),

    ('iPad Air M3',
     'Apple Tablet',
     19990000,
     18,
     'ipad.jpg',
     3),

    ('AirPods Pro 2',
     'Tai nghe',
     5990000,
     50,
     'airpods.jpg',
     4),

    ('Logitech G502',
     'Chuot Gaming',
     1290000,
     40,
     'g502.jpg',
     4);
INSERT INTO customers
(name, email, phone, address)
VALUES
    ('Nguyễn Văn A',
     'a@gmail.com',
     '0901111111',
     'TP HCM'),

    ('Trần Thị B',
     'b@gmail.com',
     '0902222222',
     'Hà Nội'),

    ('Lê Văn C',
     'c@gmail.com',
     '0903333333',
     'Đà Nẵng');
INSERT INTO orders
(order_date, total_amount, customer_id)
VALUES
    ('2026-07-20 09:30:00', 32990000, 1),
    ('2026-07-21 10:15:00', 30290000, 2),
    ('2026-07-22 14:20:00', 19990000, 3);
INSERT INTO order_details
(order_id, product_id, quantity, price)
VALUES
    (1, 1, 1, 32990000),
    (2, 2, 1, 28990000),
    (2, 6, 1, 5990000),
    (3, 5, 1, 19990000);
-- kiểm tra data
USE sale_management;

SHOW TABLES;

SELECT * FROM categories;

SELECT * FROM products;

SELECT * FROM customers;

SELECT * FROM orders;

SELECT * FROM order_details;

DESCRIBE orders;