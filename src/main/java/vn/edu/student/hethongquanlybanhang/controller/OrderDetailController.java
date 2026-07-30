package vn.edu.student.hethongquanlybanhang.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.edu.student.hethongquanlybanhang.dto.request.OrderDetailRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.OrderDetailResponse;
import vn.edu.student.hethongquanlybanhang.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService service;

    public OrderDetailController(OrderDetailService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderDetailResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrderDetailResponse getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailResponse create(
            @Valid @RequestBody OrderDetailRequest request) {

        return service.create(request);
    }

    @PutMapping("/{id}")
    public OrderDetailResponse update(
            @PathVariable Long id,
            @Valid @RequestBody OrderDetailRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}