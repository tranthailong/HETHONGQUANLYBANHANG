package vn.edu.student.hethongquanlybanhang.mapper;

import vn.edu.student.hethongquanlybanhang.dto.response.OrderResponse;
import vn.edu.student.hethongquanlybanhang.entity.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderResponse toResponse(Order order) {

        Long customerId = null;
        String customerName = null;

        if (order.getCustomer() != null) {
            customerId = order.getCustomer().getId();
            customerName = order.getCustomer().getName();
        }

        return new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                customerId,
                customerName
        );
    }
}