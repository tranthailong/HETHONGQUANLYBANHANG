package vn.edu.student.hethongquanlybanhang.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.edu.student.hethongquanlybanhang.dto.request.CustomerRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CustomerResponse;
import vn.edu.student.hethongquanlybanhang.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(
            @Valid @RequestBody CustomerRequest request) {

        return service.create(request);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id) {

        service.delete(id);
    }
}