package vn.edu.student.hethongquanlybanhang.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.student.hethongquanlybanhang.dto.request.ProductRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.ProductResponse;
import vn.edu.student.hethongquanlybanhang.entity.Category;
import vn.edu.student.hethongquanlybanhang.entity.Product;
import vn.edu.student.hethongquanlybanhang.exception.ResourceNotFoundException;
import vn.edu.student.hethongquanlybanhang.mapper.ProductMapper;
import vn.edu.student.hethongquanlybanhang.repository.CategoryRepository;
import vn.edu.student.hethongquanlybanhang.repository.ProductRepository;
import vn.edu.student.hethongquanlybanhang.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id = " + id));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id = " + request.getCategoryId()));

        Product product = ProductMapper.toEntity(request, category);

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id = " + id));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id = " + request.getCategoryId()));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setCategory(category);

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id = " + id));

        productRepository.delete(product);
    }
}