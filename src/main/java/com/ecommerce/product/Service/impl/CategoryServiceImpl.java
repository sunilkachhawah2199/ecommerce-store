package com.ecommerce.product.Service.impl;

import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.models.Category;
import com.ecommerce.product.repository.CategoryRepo;
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
    public Category createCategory(String title) {
        Category category = new Category();
        category.setTitle(title);
        category.setCreatedAt(current);
        category.setUpdatedAt(current);
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryByTitle(String title){
        return categoryRepo.findCategoryByTitle(title);
    }
}
