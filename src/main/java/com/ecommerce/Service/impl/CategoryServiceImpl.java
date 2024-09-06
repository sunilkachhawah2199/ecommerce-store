package com.ecommerce.Service.impl;

import com.ecommerce.Service.CategoryService;
import com.ecommerce.models.Category;
import com.ecommerce.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepo categoryRepo;
    LocalDateTime current= LocalDateTime.now();

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Boolean existsByTitle(String title) {
        return categoryRepo.existsByTitle(title);
    }

    @Override
    public Category createCategory(Category category) {
        category.setCreatedAt(current);
        category.setUpdatedAt(current);
        return categoryRepo.save(category);
    }

    @Override
    public List<String> getCategories() {
        return List.of();
    }


}
