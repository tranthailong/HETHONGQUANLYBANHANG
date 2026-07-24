package vn.edu.student.hethongquanlybanhang.service.impl;

import org.springframework.stereotype.Service;

import vn.edu.student.hethongquanlybanhang.dto.request.OrderRequest;
import vn.edu.student.hethongquanlybanhang.dto.response.OrderResponse;
import vn.edu.student.hethongquanlybanhang.entity.Customer;
import vn.edu.student.hethongquanlybanhang.entity.Order;
import vn.edu.student.hethongquanlybanhang.exception.ResourceNotFoundException;
import vn.edu.student.hethongquanlybanhang.mapper.OrderMapper;
import vn.edu.student.hethongquanlybanhang.repository.CustomerRepository;
import vn.edu.student.hethongquanlybanhang.repository.OrderRepository;
import vn.edu.student.hethongquanlybanhang.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            CustomerRepository customerRepository) {

        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<OrderResponse> getAll() {

        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id = " + id
                        ));

        return OrderMapper.toResponse(order);
    }

    @Override
    public OrderResponse create(OrderRequest request) {

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id = "
                                        + request.getCustomerId()
                        ));

        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());

        order.setTotalAmount(
                request.getTotalAmount()
        );

        order.setCustomer(customer);

        Order savedOrder =
                orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    public OrderResponse update(
            Long id,
            OrderRequest request) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id = " + id
                        ));

        Customer customer =
                customerRepository.findById(
                        request.getCustomerId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id = "
                                        + request.getCustomerId()
                        ));

        order.setCustomer(customer);

        order.setTotalAmount(
                request.getTotalAmount()
        );

        Order updatedOrder =
                orderRepository.save(order);

        return OrderMapper.toResponse(updatedOrder);
    }

    @Override
    public void delete(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id = " + id
                        ));

        orderRepository.delete(order);
    }
}