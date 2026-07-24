# 🛒 HỆ THỐNG QUẢN LÝ BÁN HÀNG

## 1. Giới thiệu dự án

**Hệ thống quản lý bán hàng** là một ứng dụng Backend được xây dựng bằng **Java Spring Boot**, sử dụng kiến trúc nhiều tầng (Layered Architecture) và cơ sở dữ liệu quan hệ **MySQL**.

Hệ thống hỗ trợ quản lý:

- Danh mục sản phẩm (Category)
- Sản phẩm (Product)
- Khách hàng (Customer)
- Đơn hàng (Order)
- Chi tiết đơn hàng (OrderDetail)

Dự án sử dụng **Spring Data JPA** và **Hibernate** để ánh xạ các đối tượng Java với cơ sở dữ liệu MySQL, đồng thời cung cấp các RESTful API để thực hiện các thao tác CRUD.

---

## 2. Mục tiêu dự án

- Áp dụng kiến thức lập trình Java vào xây dựng hệ thống thực tế.
- Thực hành xây dựng REST API bằng Spring Boot.
- Sử dụng Spring Data JPA và Hibernate để thao tác với cơ sở dữ liệu.
- Thiết kế cơ sở dữ liệu quan hệ MySQL.
- Áp dụng mô hình kiến trúc nhiều tầng.
- Thực hành sử dụng Entity, DTO, Mapper, Repository và Service.
- Xử lý Exception tập trung.
- Sử dụng Swagger/OpenAPI để kiểm thử API.
- Quản lý source code bằng Git và GitHub.
- Thiết lập môi trường chạy ứng dụng bằng Docker.
- Xây dựng nền tảng có khả năng mở rộng trong tương lai.

---

## 3. Công nghệ sử dụng

| Công nghệ | Mục đích |
|---|---|
| Java | Ngôn ngữ lập trình chính |
| Spring Boot | Xây dựng ứng dụng Backend |
| Spring Web | Xây dựng REST API |
| Spring Data JPA | Truy cập và thao tác Database |
| Hibernate | ORM ánh xạ Entity với Database |
| MySQL | Hệ quản trị cơ sở dữ liệu |
| Maven | Quản lý Dependency và Build Project |
| Swagger / OpenAPI | Tài liệu và kiểm thử API |
| Docker | Đóng gói và triển khai ứng dụng |
| Git | Quản lý phiên bản source code |
| GitHub | Lưu trữ source code |
| IntelliJ IDEA | Môi trường phát triển |

---

## 4. Kiến trúc hệ thống

Dự án sử dụng mô hình **Layered Architecture – Kiến trúc nhiều tầng**.

```text
                         CLIENT
                    Swagger / Postman
                           │
                           │ HTTP Request
                           ▼
                ┌─────────────────────┐
                │     CONTROLLER      │
                │ Nhận và xử lý HTTP  │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │        DTO          │
                │ Request / Response  │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │       SERVICE       │
                │ Xử lý nghiệp vụ     │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │       MAPPER        │
                │ Entity ↔ DTO        │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │     REPOSITORY      │
                │ Truy cập Database   │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │      HIBERNATE      │
                │ Java Object ↔ SQL   │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │        MYSQL        │
                │   sale_management   │
                └─────────────────────┘
```

### Luồng xử lý Request

```text
Client
   ↓
Controller
   ↓
DTO Request
   ↓
Service
   ↓
Mapper
   ↓
Repository
   ↓
Hibernate
   ↓
MySQL
```

### Luồng trả Response

```text
MySQL
   ↓
Hibernate
   ↓
Repository
   ↓
Service
   ↓
Mapper
   ↓
DTO Response
   ↓
Controller
   ↓
Client
```

---

## 5. Cấu trúc thư mục dự án

```text
src
└── main
    ├── java
    │   └── vn
    │       └── edu
    │           └── student
    │               └── hethongquanlybanhang
    │
    │                   ├── config
    │                   │   └── SwaggerConfig.java
    │                   │
    │                   ├── controller
    │                   │   ├── CategoryController.java
    │                   │   ├── CustomerController.java
    │                   │   ├── OrderController.java
    │                   │   ├── OrderDetailController.java
    │                   │   └── ProductController.java
    │                   │
    │                   ├── dto
    │                   │   ├── request
    │                   │   │   ├── CategoryRequest.java
    │                   │   │   ├── CustomerRequest.java
    │                   │   │   ├── OrderRequest.java
    │                   │   │   ├── OrderDetailRequest.java
    │                   │   │   └── ProductRequest.java
    │                   │   │
    │                   │   └── response
    │                   │       ├── CategoryResponse.java
    │                   │       ├── CustomerResponse.java
    │                   │       ├── OrderResponse.java
    │                   │       ├── OrderDetailResponse.java
    │                   │       └── ProductResponse.java
    │                   │
    │                   ├── entity
    │                   │   ├── Category.java
    │                   │   ├── Customer.java
    │                   │   ├── Order.java
    │                   │   ├── OrderDetail.java
    │                   │   └── Product.java
    │                   │
    │                   ├── exception
    │                   │   ├── GlobalExceptionHandler.java
    │                   │   └── ResourceNotFoundException.java
    │                   │
    │                   ├── mapper
    │                   │   ├── CategoryMapper.java
    │                   │   ├── CustomerMapper.java
    │                   │   ├── OrderMapper.java
    │                   │   ├── OrderDetailMapper.java
    │                   │   └── ProductMapper.java
    │                   │
    │                   ├── repository
    │                   │   ├── CategoryRepository.java
    │                   │   ├── CustomerRepository.java
    │                   │   ├── OrderRepository.java
    │                   │   ├── OrderDetailRepository.java
    │                   │   └── ProductRepository.java
    │                   │
    │                   ├── service
    │                   │   ├── CategoryService.java
    │                   │   ├── CustomerService.java
    │                   │   ├── OrderService.java
    │                   │   ├── OrderDetailService.java
    │                   │   ├── ProductService.java
    │                   │   │
    │                   │   └── impl
    │                   │       ├── CategoryServiceImpl.java
    │                   │       ├── CustomerServiceImpl.java
    │                   │       ├── OrderServiceImpl.java
    │                   │       ├── OrderDetailServiceImpl.java
    │                   │       └── ProductServiceImpl.java
    │                   │
    │                   └── HethongquanlybanhangApplication.java
    │
    └── resources
        └── application.properties
```

---

# 6. Thiết kế cơ sở dữ liệu

## Tên Database

```text
sale_management
```

Database gồm 5 bảng chính:

1. `categories` – Danh mục
2. `products` – Sản phẩm
3. `customers` – Khách hàng
4. `orders` – Đơn hàng
5. `order_details` – Chi tiết đơn hàng

---

## 6.1. Sơ đồ quan hệ cơ sở dữ liệu

```text
┌──────────────────────────────┐
│         CATEGORIES           │
│          DANH MỤC            │
├──────────────────────────────┤
│ PK id                        │
│    name                      │
│    description               │
└──────────────┬───────────────┘
               │
               │ 1
               │
               │ N
               ▼
┌──────────────────────────────┐
│          PRODUCTS            │
│          SẢN PHẨM            │
├──────────────────────────────┤
│ PK id                        │
│    name                      │
│    description               │
│    price                     │
│    quantity                  │
│    image                     │
│ FK category_id               │
└──────────────┬───────────────┘
               │
               │ 1
               │
               │ N
               ▼
┌──────────────────────────────┐
│       ORDER_DETAILS          │
│      CHI TIẾT ĐƠN HÀNG       │
├──────────────────────────────┤
│ PK id                        │
│ FK order_id                  │
│ FK product_id                │
│    quantity                  │
│    price                     │
└──────────────┬───────────────┘
               │
               │ N
               │
               │ 1
               ▼
┌──────────────────────────────┐
│           ORDERS             │
│          ĐƠN HÀNG            │
├──────────────────────────────┤
│ PK id                        │
│    order_date                │
│    total_amount              │
│ FK customer_id               │
└──────────────┬───────────────┘
               │
               │ N
               │
               │ 1
               ▼
┌──────────────────────────────┐
│         CUSTOMERS            │
│         KHÁCH HÀNG           │
├──────────────────────────────┤
│ PK id                        │
│    name                      │
│    email                     │
│    phone                     │
│    address                   │
└──────────────────────────────┘
```

---

## 6.2. Bảng `categories` – Danh mục

Dùng để lưu thông tin các danh mục sản phẩm.

| Cột | Kiểu dữ liệu | Ràng buộc | Ý nghĩa |
|---|---|---|---|
| `id` | BIGINT | PK, AUTO_INCREMENT | Mã danh mục |
| `name` | VARCHAR(255) | NOT NULL | Tên danh mục |
| `description` | VARCHAR(255) | NULL | Mô tả danh mục |

Dữ liệu mẫu:

| id | name | description |
|---|---|---|
| 1 | Điện thoại | Các loại điện thoại |
| 2 | Laptop | Laptop Gaming |
| 3 | Máy tính bảng | Tablet |
| 4 | Phụ kiện | Tai nghe, Chuột, Bàn phím |

---

## 6.3. Bảng `products` – Sản phẩm

Dùng để lưu thông tin sản phẩm.

| Cột | Kiểu dữ liệu | Ràng buộc | Ý nghĩa |
|---|---|---|---|
| `id` | BIGINT | PK, AUTO_INCREMENT | Mã sản phẩm |
| `name` | VARCHAR(255) | NOT NULL | Tên sản phẩm |
| `description` | TEXT | NULL | Mô tả sản phẩm |
| `price` | DECIMAL(12,2) | NOT NULL | Giá sản phẩm |
| `quantity` | INT | NOT NULL | Số lượng tồn kho |
| `image` | VARCHAR(255) | NULL | Tên/đường dẫn hình ảnh |
| `category_id` | BIGINT | FK | Mã danh mục |

Quan hệ:

```text
products.category_id
        ↓
categories.id
```

---

## 6.4. Bảng `customers` – Khách hàng

Dùng để lưu thông tin khách hàng.

| Cột | Kiểu dữ liệu | Ràng buộc | Ý nghĩa |
|---|---|---|---|
| `id` | BIGINT | PK, AUTO_INCREMENT | Mã khách hàng |
| `name` | VARCHAR(255) | NOT NULL | Tên khách hàng |
| `email` | VARCHAR(255) | UNIQUE | Email |
| `phone` | VARCHAR(20) | NULL | Số điện thoại |
| `address` | VARCHAR(255) | NULL | Địa chỉ |

---

## 6.5. Bảng `orders` – Đơn hàng

Dùng để lưu thông tin tổng quát của đơn hàng.

| Cột | Kiểu dữ liệu | Ràng buộc | Ý nghĩa |
|---|---|---|---|
| `id` | BIGINT | PK, AUTO_INCREMENT | Mã đơn hàng |
| `order_date` | DATETIME | NULL | Ngày đặt hàng |
| `total_amount` | DECIMAL(12,2) | NULL | Tổng tiền đơn hàng |
| `customer_id` | BIGINT | FK | Mã khách hàng |

Quan hệ:

```text
orders.customer_id
        ↓
customers.id
```

---

## 6.6. Bảng `order_details` – Chi tiết đơn hàng

Dùng để lưu các sản phẩm thuộc một đơn hàng.

| Cột | Kiểu dữ liệu | Ràng buộc | Ý nghĩa |
|---|---|---|---|
| `id` | BIGINT | PK, AUTO_INCREMENT | Mã chi tiết |
| `order_id` | BIGINT | FK | Mã đơn hàng |
| `product_id` | BIGINT | FK | Mã sản phẩm |
| `quantity` | INT | NULL | Số lượng sản phẩm |
| `price` | DECIMAL(12,2) | NULL | Giá sản phẩm tại thời điểm mua |

Quan hệ:

```text
order_details.order_id
        ↓
orders.id
```

```text
order_details.product_id
        ↓
products.id
```

---

# 7. Quan hệ giữa các bảng

### Danh mục và Sản phẩm

```text
categories 1 ───────── N products
```

Một danh mục có thể chứa nhiều sản phẩm.

### Khách hàng và Đơn hàng

```text
customers 1 ───────── N orders
```

Một khách hàng có thể tạo nhiều đơn hàng.

### Đơn hàng và Chi tiết đơn hàng

```text
orders 1 ───────── N order_details
```

Một đơn hàng có thể chứa nhiều sản phẩm.

### Sản phẩm và Chi tiết đơn hàng

```text
products 1 ───────── N order_details
```

Một sản phẩm có thể xuất hiện trong nhiều đơn hàng khác nhau.

---

# 8. SQL Database Schema và dữ liệu mẫu

```sql
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

-- Dữ liệu mẫu Categories
INSERT INTO categories (name, description)
VALUES
('Điện thoại', 'Các loại điện thoại'),
('Laptop', 'Laptop Gaming'),
('Máy tính bảng', 'Tablet'),
('Phụ kiện', 'Tai nghe, Chuột, Bàn phím');

-- Dữ liệu mẫu Products
INSERT INTO products
(name, description, price, quantity, image, category_id)
VALUES
('iPhone 16 Pro', 'Apple', 32990000, 20, 'iphone16.jpg', 1),
('Samsung S25 Ultra', 'Samsung', 28990000, 15, 's25.jpg', 1),
('MacBook Pro M4', 'Apple Laptop', 55990000, 10, 'macbook.jpg', 2),
('Asus ROG Strix', 'Gaming Laptop', 38990000, 12, 'rog.jpg', 2),
('iPad Air M3', 'Apple Tablet', 19990000, 18, 'ipad.jpg', 3),
('AirPods Pro 2', 'Tai nghe', 5990000, 50, 'airpods.jpg', 4),
('Logitech G502', 'Chuot Gaming', 1290000, 40, 'g502.jpg', 4);

-- Dữ liệu mẫu Customers
INSERT INTO customers
(name, email, phone, address)
VALUES
('Nguyễn Văn A', 'a@gmail.com', '0901111111', 'TP HCM'),
('Trần Thị B', 'b@gmail.com', '0902222222', 'Hà Nội'),
('Lê Văn C', 'c@gmail.com', '0903333333', 'Đà Nẵng');

-- Dữ liệu mẫu Orders
INSERT INTO orders
(order_date, total_amount, customer_id)
VALUES
('2026-07-20 09:30:00', 32990000, 1),
('2026-07-21 10:15:00', 34980000, 2),
('2026-07-22 14:20:00', 19990000, 3);

-- Dữ liệu mẫu Order Details
INSERT INTO order_details
(order_id, product_id, quantity, price)
VALUES
(1, 1, 1, 32990000),
(2, 2, 1, 28990000),
(2, 6, 1, 5990000),
(3, 5, 1, 19990000);

-- Kiểm tra dữ liệu
SHOW TABLES;

SELECT * FROM categories;
SELECT * FROM products;
SELECT * FROM customers;
SELECT * FROM orders;
SELECT * FROM order_details;
```

> Lưu ý: Tổng tiền đơn hàng số 2 là `34.980.000`, gồm Samsung S25 Ultra `28.990.000` và AirPods Pro 2 `5.990.000`.

---

# 9. REST API

## Category API

```text
GET     /api/categories
GET     /api/categories/{id}
POST    /api/categories
PUT     /api/categories/{id}
DELETE  /api/categories/{id}
```

## Product API

```text
GET     /api/products
GET     /api/products/{id}
POST    /api/products
PUT     /api/products/{id}
DELETE  /api/products/{id}
```

## Customer API

```text
GET     /api/customers
GET     /api/customers/{id}
POST    /api/customers
PUT     /api/customers/{id}
DELETE  /api/customers/{id}
```

## Order API

```text
GET     /api/orders
GET     /api/orders/{id}
POST    /api/orders
PUT     /api/orders/{id}
DELETE  /api/orders/{id}
```

## OrderDetail API

```text
GET     /api/order-details
GET     /api/order-details/{id}
POST    /api/order-details
PUT     /api/order-details/{id}
DELETE  /api/order-details/{id}
```

> Endpoint thực tế phụ thuộc vào `@RequestMapping` được khai báo trong Controller.

---

# 10. DTO, Mapper và Exception

## DTO

DTO được sử dụng để định nghĩa dữ liệu đầu vào và đầu ra của API.

### Request DTO

```text
CategoryRequest
CustomerRequest
OrderRequest
OrderDetailRequest
ProductRequest
```

### Response DTO

```text
CategoryResponse
CustomerResponse
OrderResponse
OrderDetailResponse
ProductResponse
```

## Mapper

Mapper chịu trách nhiệm chuyển đổi dữ liệu:

```text
Entity → Response DTO
Request DTO → Entity
```

Các Mapper:

```text
CategoryMapper
CustomerMapper
OrderMapper
OrderDetailMapper
ProductMapper
```

## Exception

Dự án sử dụng xử lý Exception tập trung:

```text
GlobalExceptionHandler
ResourceNotFoundException
```

Mục đích:

- Xử lý lỗi không tìm thấy dữ liệu.
- Trả về thông báo lỗi rõ ràng.
- Chuẩn hóa Response lỗi.
- Giảm code xử lý lỗi lặp lại trong Controller.

---

# 11. Swagger / OpenAPI

Swagger được sử dụng để:

- Xem danh sách API.
- Xem cấu trúc Request và Response.
- Gửi Request trực tiếp đến Backend.
- Kiểm thử các API CRUD.
- Hỗ trợ quá trình phát triển Backend.

Sau khi chạy ứng dụng, truy cập:

```text
http://localhost:8080/swagger-ui/index.html
```

Chọn API → `Try it out` → nhập dữ liệu → `Execute`.

---

# 12. Cấu hình Database

Database sử dụng:

```text
Database: sale_management
Host: localhost
Port: 3306
Username: root
```

File cấu hình:

```text
src/main/resources/application.properties
```

Ví dụ:

```properties
spring.application.name=HETHONGQUANLYBANHANG

spring.datasource.url=jdbc:mysql://localhost:3306/sale_management
spring.datasource.username=root
spring.datasource.password=root123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8080
```

> Không nên đưa mật khẩu Database thật lên GitHub trong dự án thực tế. Nên sử dụng biến môi trường hoặc Secret Manager.

---

# 13. Hướng dẫn chạy dự án trên Local

## Bước 1: Clone Repository

```bash
git clone <GITHUB_REPOSITORY_URL>
```

Di chuyển vào thư mục:

```bash
cd HETHONGQUANLYBANHANG
```

## Bước 2: Chuẩn bị môi trường

Cần cài đặt:

- JDK
- Maven
- MySQL
- IntelliJ IDEA

## Bước 3: Tạo Database

Mở MySQL và chạy:

```sql
CREATE DATABASE sale_management;
```

Sau đó chạy SQL Schema và dữ liệu mẫu ở phần trên.

## Bước 4: Cấu hình Database

Mở:

```text
src/main/resources/application.properties
```

Cập nhật username và password phù hợp với máy tính.

## Bước 5: Build Project

```bash
mvn clean install
```

Hoặc sử dụng Maven trong IntelliJ IDEA.

## Bước 6: Chạy ứng dụng

Chạy:

```text
HethongquanlybanhangApplication.java
```

Ứng dụng chạy tại:

```text
http://localhost:8080
```

## Bước 7: Kiểm tra Swagger

Truy cập:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 14. Docker

Docker được sử dụng để đóng gói ứng dụng và hỗ trợ thiết lập môi trường chạy đồng nhất.

Kiến trúc triển khai:

```text
                    Docker Compose
                          │
             ┌────────────┴────────────┐
             │                         │
             ▼                         ▼
    ┌─────────────────┐       ┌─────────────────┐
    │  Spring Boot    │       │      MySQL      │
    │   Container     │──────▶│    Container    │
    │    Port 8080    │       │    Port 3306    │
    └─────────────────┘       └─────────────────┘
```

Các file Docker:

```text
Dockerfile
docker-compose.yml
```

Chạy hệ thống:

```bash
docker compose up --build
```

Kiểm tra Container:

```bash
docker ps
```

Dừng hệ thống:

```bash
docker compose down
```

> Nếu dự án chưa có `Dockerfile` và `docker-compose.yml`, cần tạo và kiểm thử trước khi ghi Docker đã hoàn thành trong báo cáo.

---

# 15. Kiểm thử hệ thống

Hệ thống có thể được kiểm thử bằng:

- Swagger UI
- Postman
- IntelliJ HTTP Client

Các chức năng cần kiểm thử:

### Category

- Thêm danh mục
- Xem danh sách danh mục
- Xem danh mục theo ID
- Cập nhật danh mục
- Xóa danh mục

### Product

- Thêm sản phẩm
- Xem danh sách sản phẩm
- Xem sản phẩm theo ID
- Cập nhật sản phẩm
- Xóa sản phẩm

### Customer

- Thêm khách hàng
- Xem danh sách khách hàng
- Xem khách hàng theo ID
- Cập nhật khách hàng
- Xóa khách hàng

### Order

- Tạo đơn hàng
- Xem danh sách đơn hàng
- Xem đơn hàng theo ID
- Cập nhật đơn hàng
- Xóa đơn hàng

### OrderDetail

- Thêm chi tiết đơn hàng
- Xem danh sách chi tiết
- Xem chi tiết theo ID
- Cập nhật chi tiết
- Xóa chi tiết

---

# 16. Luồng xử lý một đơn hàng

Khi khách hàng tạo một đơn hàng:

```text
Client
   │
   │ POST /api/orders
   ▼
OrderController
   │
   ▼
OrderRequest
   │
   ▼
OrderService
   │
   ├── Kiểm tra Customer
   │
   ├── Tạo Order
   │
   └── Lưu Order
          │
          ▼
    OrderRepository
          │
          ▼
       Hibernate
          │
          ▼
         MySQL
          │
          ▼
     OrderResponse
          │
          ▼
        Client
```

Quan hệ đơn hàng:

```text
Order
   │
   │ 1
   ▼
OrderDetail
   │
   ├──────────► Product
   │
   └──────────► Quantity + Price
```

---

# 17. Hướng phát triển

Trong tương lai, hệ thống có thể mở rộng:

- Đăng nhập và đăng xuất.
- Phân quyền Admin và User.
- Spring Security và JWT.
- Tích hợp thanh toán trực tuyến.
- Quản lý nhập và xuất kho.
- Thống kê doanh thu.
- Dashboard quản lý.
- Quản lý trạng thái đơn hàng.
- Upload hình ảnh sản phẩm.
- Tìm kiếm và lọc sản phẩm.
- Phân trang dữ liệu.
- Docker và Docker Compose.
- Triển khai hệ thống lên Cloud.

---

# 18. Trạng thái dự án

```text
✅ Thiết kế Database Schema
✅ Tạo Entity
✅ Tạo Repository
✅ Tạo Service
✅ Tạo Controller
✅ DTO Request
✅ DTO Response
✅ Mapper
✅ Exception Handler
✅ REST API
✅ Swagger / OpenAPI
✅ MySQL
✅ Maven
✅ Git / GitHub
🔄 Docker
🔄 Hoàn thiện kiểm thử
```

> Cập nhật trạng thái theo tình hình thực tế của dự án trước khi nộp.

---

# 19. Thành viên nhóm

| STT | Họ và tên | Vai trò |
|---|---|---|
| 1 | Thành viên 1 | Backend Developer |
| 2 | Thành viên 2 | Database |
| 3 | Thành viên 3 | API / Testing |
| 4 | Thành viên 4 | Documentation |

> Cập nhật thông tin thành viên nhóm theo thực tế.

---

# 20. Tài liệu tham khảo

- Java Documentation
- Spring Boot Documentation
- Spring Data JPA Documentation
- Hibernate Documentation
- MySQL Documentation
- Swagger / OpenAPI Documentation
- Docker Documentation

---

# 21. License

Dự án được thực hiện với mục đích học tập và phục vụ môn học.

**© 2026 - HỆ THỐNG QUẢN LÝ BÁN HÀNG**
