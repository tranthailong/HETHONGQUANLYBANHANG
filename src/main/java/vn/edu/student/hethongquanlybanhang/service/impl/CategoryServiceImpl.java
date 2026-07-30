package vn.edu.student.hethongquanlybanhang.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.student.hethongquanlybanhang.dto.request.CategoryRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.CategoryResponse;
import vn.edu.student.hethongquanlybanhang.entity.Category;
import vn.edu.student.hethongquanlybanhang.mapper.CategoryMapper;
import vn.edu.student.hethongquanlybanhang.repository.CategoryRepository;
import vn.edu.student.hethongquanlybanhang.service.CategoryService;
import vn.edu.student.hethongquanlybanhang.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoryResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = repository.findById(id).orElseThrow();
        return CategoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {

        Category category = CategoryMapper.toEntity(request);

        return CategoryMapper.toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {

        Category category = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id = " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return CategoryMapper.toResponse(repository.save(category));
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}