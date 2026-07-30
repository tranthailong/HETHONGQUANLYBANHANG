package vn.edu.student.hethongquanlybanhang.service;

import vn.edu.student.hethongquanlybanhang.dto.request.OrderRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAll();

    OrderResponse getById(Long id);

    OrderResponse create(OrderRequest request);

    OrderResponse update(Long id, OrderRequest request);

    void delete(Long id);
}