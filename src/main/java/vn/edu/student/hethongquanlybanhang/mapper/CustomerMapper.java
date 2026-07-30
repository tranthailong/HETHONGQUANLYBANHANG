package vn.edu.student.hethongquanlybanhang.mapper;

import vn.edu.student.hethongquanlybanhang.dto.request.CustomerRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CustomerResponse;
import vn.edu.student.hethongquanlybanhang.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest request) {

        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return customer;
    }

    public static CustomerResponse toResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
        );
    }
}