package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.student.hethongquanlybanhang.entity.Order;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
}