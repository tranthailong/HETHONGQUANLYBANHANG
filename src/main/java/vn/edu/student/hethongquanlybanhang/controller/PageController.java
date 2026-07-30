package vn.edu.student.hethongquanlybanhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "index";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories";
    }

    @GetMapping("/customers")
    public String customers() {
        return "customers";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/statistics")
    public String statistics() {
        return "statistics";
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }
}