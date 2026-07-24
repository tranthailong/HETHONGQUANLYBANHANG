package vn.edu.student.hethongquanlybanhang.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import vn.edu.student.hethongquanlybanhang.dto.request.CategoryRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CategoryResponse;
import vn.edu.student.hethongquanlybanhang.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public CategoryResponse create(
            @Valid @RequestBody CategoryRequest request) {

        return service.create(request);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}