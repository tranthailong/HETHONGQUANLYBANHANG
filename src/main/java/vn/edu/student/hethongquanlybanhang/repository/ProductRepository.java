package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.student.hethongquanlybanhang.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Tổng sản phẩm (dùng sẵn của JpaRepository, không cần ghi đè)

    // 2. Đang bán (số lượng > 10) - Đổi sang Long Object & Native Query chống lỗi 500
    @Query(value = "SELECT COUNT(*) FROM products WHERE quantity > 10", nativeQuery = true)
    Long countActiveProducts();

    // 3. Sắp hết hàng (1 -> 10)
    @Query(value = "SELECT COUNT(*) FROM products WHERE quantity BETWEEN 1 AND 10", nativeQuery = true)
    Long countLowStockProducts();

    // 4. Hết hàng (số lượng = 0)
    @Query(value = "SELECT COUNT(*) FROM products WHERE quantity = 0", nativeQuery = true)
    Long countOutOfStockProducts();
}