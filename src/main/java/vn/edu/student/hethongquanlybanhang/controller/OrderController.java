package vn.edu.student.hethongquanlybanhang.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import vn.edu.student.hethongquanlybanhang.dto.request.OrderRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.OrderResponse;
import vn.edu.student.hethongquanlybanhang.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderResponse> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponse getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(
            @Valid @RequestBody OrderRequest request) {

        return service.create(request);
    }

    @PutMapping("/{id}")
    public OrderResponse update(
            @PathVariable Long id,
            @Valid @RequestBody OrderRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}