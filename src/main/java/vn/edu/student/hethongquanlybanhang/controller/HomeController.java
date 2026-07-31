package vn.edu.student.hethongquanlybanhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Hỗ trợ cả không đuôi và có đuôi .html cho trang chủ / dashboard
    @GetMapping({"/", "/dashboard", "/dashboard.html", "/index.html"})
    public String dashboard() {
        return "index";
    }

    // Trang sản phẩm (hỗ trợ cả /products và /products.html)
    @GetMapping({"/products", "/products.html"})
    public String products() {
        return "products";
    }

    // Trang danh mục
    @GetMapping({"/categories", "/categories.html"})
    public String categories() {
        return "categories";
    }

    // Trang khách hàng
    @GetMapping({"/customers", "/customers.html"})
    public String customers() {
        return "customers";
    }

    // Trang đơn hàng
    @GetMapping({"/orders", "/orders.html"})
    public String orders() {
        return "orders";
    }

    // Trang thống kê
    @GetMapping({"/statistics", "/statistics.html"})
    public String statistics() {
        return "statistics";
    }

    // Trang cài đặt
    @GetMapping({"/settings", "/settings.html"})
    public String settings() {
        return "settings";
    }
}