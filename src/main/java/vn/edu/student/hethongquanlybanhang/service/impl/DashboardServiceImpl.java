package vn.edu.student.hethongquanlybanhang.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.student.hethongquanlybanhang.dto.response.DashboardResponse;
import vn.edu.student.hethongquanlybanhang.repository.CategoryRepository;
import vn.edu.student.hethongquanlybanhang.repository.CustomerRepository;
import vn.edu.student.hethongquanlybanhang.repository.OrderRepository;
import vn.edu.student.hethongquanlybanhang.repository.ProductRepository;
import vn.edu.student.hethongquanlybanhang.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    public DashboardServiceImpl(ProductRepository productRepository,
                                CategoryRepository categoryRepository,
                                CustomerRepository customerRepository,
                                OrderRepository orderRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response = new DashboardResponse();

        response.setTotalProducts(productRepository.count());

        response.setActiveProducts(
                productRepository.countActiveProducts());

        response.setLowStockProducts(
                productRepository.countLowStockProducts());

        response.setOutOfStockProducts(
                productRepository.countOutOfStockProducts());

        response.setTotalCategories(
                categoryRepository.count());

        response.setTotalCustomers(
                customerRepository.count());

        response.setTotalOrders(
                orderRepository.count());

        Double revenue = orderRepository.getTotalRevenue();

        response.setTotalRevenue(
                revenue == null ? 0 : revenue);

        return response;
    }

}