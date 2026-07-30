package vn.edu.student.hethongquanlybanhang.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.student.hethongquanlybanhang.dto.request.OrderDetailRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.OrderDetailResponse;
import vn.edu.student.hethongquanlybanhang.entity.Order;
import vn.edu.student.hethongquanlybanhang.entity.OrderDetail;
import vn.edu.student.hethongquanlybanhang.entity.Product;
import vn.edu.student.hethongquanlybanhang.exception.ResourceNotFoundException;
import vn.edu.student.hethongquanlybanhang.mapper.OrderDetailMapper;
import vn.edu.student.hethongquanlybanhang.repository.OrderDetailRepository;
import vn.edu.student.hethongquanlybanhang.repository.OrderRepository;
import vn.edu.student.hethongquanlybanhang.repository.ProductRepository;
import vn.edu.student.hethongquanlybanhang.service.OrderDetailService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository repository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDetailServiceImpl(
            OrderDetailRepository repository,
            OrderRepository orderRepository,
            ProductRepository productRepository) {

        this.repository = repository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderDetailResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(OrderDetailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse getById(Long id) {

        OrderDetail orderDetail = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "OrderDetail not found with id = " + id
                        ));

        return OrderDetailMapper.toResponse(orderDetail);
    }

    @Override
    public OrderDetailResponse create(OrderDetailRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id = "
                                        + request.getOrderId()
                        ));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id = "
                                        + request.getProductId()
                        ));

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setPrice(request.getPrice());
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        return OrderDetailMapper.toResponse(
                repository.save(orderDetail)
        );
    }

    @Override
    public OrderDetailResponse update(
            Long id,
            OrderDetailRequest request) {

        OrderDetail orderDetail = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "OrderDetail not found with id = " + id
                        ));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id = "
                                        + request.getOrderId()
                        ));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id = "
                                        + request.getProductId()
                        ));

        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setPrice(request.getPrice());
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        return OrderDetailMapper.toResponse(
                repository.save(orderDetail)
        );
    }

    @Override
    public void delete(Long id) {

        OrderDetail orderDetail = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "OrderDetail not found with id = " + id
                        ));

        repository.delete(orderDetail);
    }
}