package vn.edu.student.hethongquanlybanhang.service;

import vn.edu.student.hethongquanlybanhang.dto.request.CategoryRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

}