package vn.edu.student.hethongquanlybanhang.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vn.edu.student.hethongquanlybanhang.dto.request.ProductRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.ProductResponse;
import vn.edu.student.hethongquanlybanhang.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,
                                  @Valid @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}