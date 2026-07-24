package vn.edu.student.hethongquanlybanhang.mapper;

import vn.edu.student.hethongquanlybanhang.dto.response.OrderDetailResponse;
import vn.edu.student.hethongquanlybanhang.entity.OrderDetail;

public class OrderDetailMapper {

    private OrderDetailMapper() {
    }

    public static OrderDetailResponse toResponse(OrderDetail orderDetail) {

        return new OrderDetailResponse(
                orderDetail.getId(),
                orderDetail.getQuantity(),
                orderDetail.getPrice(),
                orderDetail.getOrder().getId(),
                orderDetail.getProduct().getId(),
                orderDetail.getProduct().getName()
        );
    }
}