package com.ecommerce.product.Service;

import com.ecommerce.product.models.Category;

import java.util.List;

public interface CategoryService {

    // create category
    public Category createCategory(String title);

    // get all categories
    public List<Category> getCategories();

    public Category getCategoryByTitle(String title);
}
