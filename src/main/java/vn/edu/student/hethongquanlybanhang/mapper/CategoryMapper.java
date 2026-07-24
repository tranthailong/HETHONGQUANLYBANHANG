package vn.edu.student.hethongquanlybanhang.mapper;

import vn.edu.student.hethongquanlybanhang.dto.request.CategoryRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CategoryResponse;
import vn.edu.student.hethongquanlybanhang.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest request){

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;
    }

    public static CategoryResponse toResponse(Category category){

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );

    }

}