package vn.edu.student.hethongquanlybanhang.service;

import vn.edu.student.hethongquanlybanhang.dto.request.OrderDetailRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailResponse> getAll();

    OrderDetailResponse getById(Long id);

    OrderDetailResponse create(OrderDetailRequest request);

    OrderDetailResponse update(Long id, OrderDetailRequest request);

    void delete(Long id);
}