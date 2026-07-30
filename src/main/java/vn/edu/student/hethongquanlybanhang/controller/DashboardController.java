package vn.edu.student.hethongquanlybanhang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.student.hethongquanlybanhang.dto.response.DashboardResponse;
import vn.edu.student.hethongquanlybanhang.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public DashboardResponse getDashboard() {
        return service.getDashboard();
    }

}