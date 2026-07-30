package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.student.hethongquanlybanhang.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Tổng sản phẩm
    long count();

    // Đang bán (số lượng > 10)
    @Query("SELECT COUNT(p) FROM Product p WHERE p.quantity > 10")
    long countActiveProducts();

    // Sắp hết hàng (1 -> 10)
    @Query("SELECT COUNT(p) FROM Product p WHERE p.quantity BETWEEN 1 AND 10")
    long countLowStockProducts();

    // Hết hàng
    @Query("SELECT COUNT(p) FROM Product p WHERE p.quantity = 0")
    long countOutOfStockProducts();
}