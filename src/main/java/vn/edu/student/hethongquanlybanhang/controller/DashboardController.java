package vn.edu.student.hethongquanlybanhang.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.student.hethongquanlybanhang.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*") // Chống lỗi CORS nếu frontend gọi từ port khác
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getDashboard() {
        try {
            return ResponseEntity.ok(service.getDashboard());
        } catch (Exception e) {
            // In toàn bộ nguyên nhân nổ lỗi ra Console IntelliJ/Docker
            System.err.println("=== LỖI TẠI DASHBOARD CONTROLLER ===");
            e.printStackTrace();

            // Trả về thông báo lỗi chi tiết xuống Client để dễ kiểm tra
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi Backend: " + e.getMessage());
        }
    }
}