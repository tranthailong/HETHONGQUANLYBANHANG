package vn.edu.student.hethongquanlybanhang.service.impl; // Hoặc package tương ứng của bạn

import org.springframework.stereotype.Service;
import vn.edu.student.hethongquanlybanhang.dto.response.DashboardResponse;
import vn.edu.student.hethongquanlybanhang.repository.*;
import vn.edu.student.hethongquanlybanhang.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;

    public DashboardServiceImpl(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public DashboardResponse getDashboard() {
        DashboardResponse response = new DashboardResponse();

        // 1. Lấy tổng doanh thu (Bọc null safety)
        Double revenue = orderRepository.getTotalRevenue();
        response.setTotalRevenue(revenue != null ? revenue : 0.0);

        // 2. Lấy tổng đơn hàng
        response.setTotalOrders(orderRepository.count());

        // 3. Lấy tổng khách hàng
        response.setTotalCustomers(customerRepository.count());

        // 4. Lấy tổng danh mục
        response.setTotalCategories(categoryRepository.count());

        // 5. Lấy tổng sản phẩm
        response.setTotalProducts(productRepository.count());

        // 6. Sản phẩm theo trạng thái (Bọc null safety chống lỗi 500)
        Long active = productRepository.countActiveProducts();
        Long lowStock = productRepository.countLowStockProducts();
        Long outOfStock = productRepository.countOutOfStockProducts();

        response.setActiveProducts(active != null ? active : 0L);
        response.setLowStockProducts(lowStock != null ? lowStock : 0L);
        response.setOutOfStockProducts(outOfStock != null ? outOfStock : 0L);

        return response;
    }
}