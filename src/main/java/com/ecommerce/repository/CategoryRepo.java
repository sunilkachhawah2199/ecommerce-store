package com.ecommerce.repository;

import com.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Category getCategoryById(Long id);
//    Category findCategoryByTitle(String title);
    Boolean existsByTitle(String title);
    Category save(String title);
}
