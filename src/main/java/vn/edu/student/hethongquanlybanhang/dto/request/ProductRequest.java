package vn.edu.student.hethongquanlybanhang.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 2, max = 255, message = "Tên sản phẩm phải từ 2 đến 255 ký tự")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Positive(message = "Giá sản phẩm phải lớn hơn 0")
    private Double big; // Khớp tên biến là big

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
    private Integer quantity;

    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    private String description;

    private String image;

    @NotNull(message = "Category ID không được để trống")
    @Positive(message = "Category ID phải lớn hơn 0")
    private Long categoryId;

    public ProductRequest() {
    }

    public ProductRequest(
            String name,
            Double big,
            Integer quantity,
            String description,
            String image,
            Long categoryId) {
        this.name = name;
        this.big = big;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBig() {
        return big;
    }

    public void setBig(Double big) {
        this.big = big;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}