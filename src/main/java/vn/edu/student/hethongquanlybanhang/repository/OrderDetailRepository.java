package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.student.hethongquanlybanhang.entity.OrderDetail;

public interface OrderDetailRepository
        extends JpaRepository<OrderDetail, Long> {
}