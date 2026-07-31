package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.student.hethongquanlybanhang.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT IFNULL(SUM(total_amount), 0) FROM orders", nativeQuery = true)
    Double getTotalRevenue();

}