package com.ecommerce.Service;

import com.ecommerce.models.Category;

import java.util.List;

public interface CategoryService {
    // check if category exist
    public Boolean existsByTitle(String title);

    // create category
    public Category createCategory(Category category);

    // get all categories
    public List<String> getCategories();

}
