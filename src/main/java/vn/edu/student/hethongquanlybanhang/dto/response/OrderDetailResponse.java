package vn.edu.student.hethongquanlybanhang.dto.response;

public class OrderDetailResponse {

    private Long id;
    private Integer quantity;
    private Double price;

    private Long orderId;
    private Long productId;
    private String productName;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(
            Long id,
            Integer quantity,
            Double price,
            Long orderId,
            Long productId,
            String productName) {

        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}