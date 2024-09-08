package com.ecommerce.product.repository;

import com.ecommerce.product.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAll();

    @Query("SELECT c from Category c WHERE c.title = :title")
    Category findCategoryByTitle(@Param("title") String title);


}
