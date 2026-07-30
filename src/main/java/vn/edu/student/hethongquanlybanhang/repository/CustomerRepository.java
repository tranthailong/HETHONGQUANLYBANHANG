package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.student.hethongquanlybanhang.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}