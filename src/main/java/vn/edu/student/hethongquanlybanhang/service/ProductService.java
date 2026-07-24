package vn.edu.student.hethongquanlybanhang.service;

import vn.edu.student.hethongquanlybanhang.dto.request.ProductRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAll();

    ProductResponse getById(Long id);

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}