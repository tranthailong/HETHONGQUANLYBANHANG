package vn.edu.student.hethongquanlybanhang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.student.hethongquanlybanhang.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}