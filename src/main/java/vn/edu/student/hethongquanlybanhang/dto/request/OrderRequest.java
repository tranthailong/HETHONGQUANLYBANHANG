package vn.edu.student.hethongquanlybanhang.dto.request;

import jakarta.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull(message = "Customer ID không được để trống")
    private Long customerId;

    private Double totalAmount;

    public OrderRequest() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}