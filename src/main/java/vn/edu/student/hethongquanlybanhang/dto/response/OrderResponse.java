package vn.edu.student.hethongquanlybanhang.dto.response;

import java.time.LocalDateTime;

public class OrderResponse {

    private Long id;

    private LocalDateTime orderDate;

    private Double totalAmount;

    private Long customerId;

    private String customerName;

    public OrderResponse() {
    }

    public OrderResponse(
            Long id,
            LocalDateTime orderDate,
            Double totalAmount,
            Long customerId,
            String customerName) {

        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}