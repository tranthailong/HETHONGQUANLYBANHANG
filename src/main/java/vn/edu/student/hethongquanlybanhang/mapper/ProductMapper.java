package vn.edu.student.hethongquanlybanhang.mapper;

import vn.edu.student.hethongquanlybanhang.dto.request.ProductRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.ProductResponse;
import vn.edu.student.hethongquanlybanhang.entity.Category;
import vn.edu.student.hethongquanlybanhang.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest request, Category category) {

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setCategory(category);

        return product;
    }

    public static ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setDescription(product.getDescription());
        response.setImage(product.getImage());

        if (product.getCategory() != null) {
            response.setCategoryName(product.getCategory().getName());
        }

        return response;
    }
}