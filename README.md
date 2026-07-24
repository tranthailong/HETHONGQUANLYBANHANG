# HỆ THỐNG QUẢN LÝ BÁN HÀNG

## 1. Giới thiệu

Dự án xây dựng hệ thống quản lý bán hàng bằng Java Spring Boot,
sử dụng kiến trúc nhiều tầng và cơ sở dữ liệu MySQL.

Hệ thống hỗ trợ quản lý:
- Danh mục sản phẩm (Category)
- Sản phẩm (Product)
- Khách hàng (Customer)
- Đơn hàng (Order)
- Chi tiết đơn hàng (OrderDetail)

## 2. Công nghệ sử dụng

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- Swagger / OpenAPI
- IntelliJ IDEA

## 3. Kiến trúc dự án

Dự án được tổ chức theo mô hình nhiều tầng:

- Controller: Xử lý các request từ client
- Service: Xử lý nghiệp vụ
- Repository: Làm việc với cơ sở dữ liệu
- Entity: Ánh xạ các bảng trong cơ sở dữ liệu
- DTO: Định nghĩa dữ liệu Request và Response
- Mapper: Chuyển đổi giữa Entity và DTO
- Exception: Xử lý lỗi tập trung
- Config: Cấu hình Swagger

## 4. Cấu trúc thư mục

```text
src/main/java/vn/edu/student/hethongquanlybanhang
│
├── config
│   └── SwaggerConfig
│
├── controller
│   ├── CategoryController
│   ├── CustomerController
│   ├── OrderController
│   ├── OrderDetailController
│   └── ProductController
│
├── dto
│   ├── request
│   └── response
│
├── entity
│   ├── Category
│   ├── Customer
│   ├── Order
│   ├── OrderDetail
│   └── Product
│
├── exception
│   ├── GlobalExceptionHandler
│   └── ResourceNotFoundException
│
├── mapper
│   ├── CategoryMapper
│   ├── CustomerMapper
│   ├── OrderMapper
│   ├── OrderDetailMapper
│   └── ProductMapper
│
├── repository
│   ├── CategoryRepository
│   ├── CustomerRepository
│   ├── OrderRepository
│   ├── OrderDetailRepository
│   └── ProductRepository
│
└── service
    ├── CategoryService
    ├── CustomerService
    ├── OrderService
    ├── OrderDetailService
    ├── ProductService
    └── impl