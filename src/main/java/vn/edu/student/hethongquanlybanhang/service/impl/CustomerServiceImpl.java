package vn.edu.student.hethongquanlybanhang.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.student.hethongquanlybanhang.dto.request.CustomerRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CustomerResponse;
import vn.edu.student.hethongquanlybanhang.entity.Customer;
import vn.edu.student.hethongquanlybanhang.exception.ResourceNotFoundException;
import vn.edu.student.hethongquanlybanhang.mapper.CustomerMapper;
import vn.edu.student.hethongquanlybanhang.repository.CustomerRepository;
import vn.edu.student.hethongquanlybanhang.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    // Lấy tất cả khách hàng
    @Override
    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Lấy khách hàng theo ID
    @Override
    public CustomerResponse getById(Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id = " + id
                        )
                );

        return CustomerMapper.toResponse(customer);
    }

    // Thêm khách hàng
    @Override
    public CustomerResponse create(CustomerRequest request) {

        Customer customer = CustomerMapper.toEntity(request);

        // save() sẽ lưu vào database
        // MySQL tự động sinh ID
        Customer savedCustomer = repository.save(customer);

        // Trả về dữ liệu có ID vừa được sinh
        return CustomerMapper.toResponse(savedCustomer);
    }

    // Cập nhật khách hàng
    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id = " + id
                        )
                );

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        Customer updatedCustomer = repository.save(customer);

        return CustomerMapper.toResponse(updatedCustomer);
    }

    // Xóa khách hàng
    @Override
    public void delete(Long id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id = " + id
                        )
                );

        repository.delete(customer);
    }
}