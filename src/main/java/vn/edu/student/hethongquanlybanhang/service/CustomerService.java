package vn.edu.student.hethongquanlybanhang.service;

import vn.edu.student.hethongquanlybanhang.dto.request.CustomerRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getAll();

    CustomerResponse getById(Long id);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerRequest request);

    void delete(Long id);
}