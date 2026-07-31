CREATE DATABASE sale_management;
USE sale_management;
DROP DATABASE sale_management;
CREATE TABLE customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE,
                           phone VARCHAR(50),
                           address VARCHAR(255)
);
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT
);
CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          big DOUBLE NOT NULL,          -- Khớp với thuộc tính big trong Java
                          quantity INT NOT NULL,
                          description TEXT,
                          image VARCHAR(255),
                          category_id INT,
                          CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories(id)
);
CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                        total_amount DOUBLE NOT NULL,
                        customer_id INT,
                        CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);
CREATE TABLE order_details (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               quantity INT NOT NULL,
                               price DOUBLE NOT NULL,
                               order_id INT,
                               product_id INT,
                               CONSTRAINT fk_orderdetail_order FOREIGN KEY (order_id) REFERENCES orders(id),
                               CONSTRAINT fk_orderdetail_product FOREIGN KEY (product_id) REFERENCES products(id)
);
-- Đảm bảo đang dùng đúng database
USE sale_management;

-- 1. Thêm dữ liệu mẫu cho bảng Categories (Danh mục sản phẩm)
INSERT INTO categories (id, name, description) VALUES
                                                   (1, 'Điện thoại', 'Các dòng smartphone chính hãng, đời mới'),
                                                   (2, 'Laptop', 'Laptop văn phòng, gaming, đồ họa'),
                                                   (3, 'Phụ kiện', 'Tai nghe, cáp sạc, chuột bàn phím');

-- 2. Thêm dữ liệu mẫu cho bảng Products (Sản phẩm - dùng cột 'big' cho giá tiền)
INSERT INTO products (id, name, big, quantity, description, image, category_id) VALUES
                                                                                    (1, 'iPhone 14 Pro', 24990000, 15, 'Điện thoại thông minh cao cấp của Apple', 'iphone14pro.jpg', 1),
                                                                                    (2, 'Samsung Galaxy S23', 21990000, 10, 'Flagship đỉnh cao đến từ Samsung', 'sgs23.jpg', 1),
                                                                                    (3, 'MacBook Air M2', 27990000, 8, 'Laptop mỏng nhẹ, pin trâu cho dân văn phòng', 'macbookairm2.jpg', 2),
                                                                                    (4, 'Asus TUF Gaming', 19990000, 12, 'Laptop chiến game cấu hình khỏe', 'asustuf.jpg', 2),
                                                                                    (5, 'Tai nghe Bluetooth Sony', 1500000, 25, 'Tai nghe chống ồn chủ động', 'sonystone.jpg', 3);

-- 3. Thêm dữ liệu mẫu cho bảng Customers (Khách hàng)
INSERT INTO customers (id, name, email, phone, address) VALUES
                                                            (1, 'Nguyễn Văn An', 'an.nguyen@gmail.com', '0901234567', 'Quận 1, TP.HCM'),
                                                            (2, 'Trần Thị Bình', 'binh.tran@gmail.com', '0912345678', 'Quận 3, TP.HCM'),
                                                            (3, 'Lê Hoàng Cường', 'cuong.le@gmail.com', '0988888888', 'Thủ Đức, TP.HCM');

-- 4. Thêm dữ liệu mẫu cho bảng Orders (Đơn hàng)
INSERT INTO orders (id, order_date, total_amount, customer_id) VALUES
                                                                   (1, '2026-07-30 08:30:00', 26490000, 1),
                                                                   (2, '2026-07-31 09:15:00', 21990000, 2);

-- 5. Thêm dữ liệu mẫu cho bảng Order Details (Chi tiết đơn hàng)
INSERT INTO order_details (id, quantity, price, order_id, product_id) VALUES
                                                                          (1, 1, 24990000, 1, 1), -- Đơn 1 mua iPhone 14 Pro
                                                                          (2, 1, 1500000, 1, 5),   -- Đơn 1 mua Tai nghe Sony (sửa lại product_id cho đúng sản phẩm 5)
                                                                          (3, 1, 21990000, 2, 2);  -- Đơn 2 mua Samsung Galaxy S23

SHOW TABLES;
SELECT * FROM categories;

SELECT * FROM products;


SELECT * FROM customers;

SELECT * FROM orders;

SELECT * FROM order_details;

