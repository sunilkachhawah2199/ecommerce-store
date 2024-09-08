package com.ecommerce.product.Service.impl;

import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.Service.ProductService;
import com.ecommerce.product.exceptions.ProductNotFoundException;
import com.ecommerce.product.models.Category;
import com.ecommerce.product.models.Product;
import com.ecommerce.product.repository.CategoryRepo;
import com.ecommerce.product.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service("SelfService")
public class ProductServiceSelfImpl implements ProductService {

    private final ProductRepo productRepo;
    private CategoryService categoryService;
    LocalDateTime current= LocalDateTime.now();

    public ProductServiceSelfImpl(ProductRepo productRepo,
                                  RestTemplate restTemplate,
                                  CategoryService categoryService,
                                  CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getProducts() {
        // only show that products that are not deleted --> isDeleted=false
        List<Product> products = productRepo.findAllNotDeleted();



        if(products.isEmpty()){
            throw new ProductNotFoundException("No products available", 404);
        }
        return products;
    }

    @Override
    public Product getProductsById(long id){

        Product product = productRepo.findProductById(id);

        if(product==null || product.isDeleted()){
            throw new ProductNotFoundException("product list is empty", 404);
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        // Check if category exists
        Category category = product.getCategory();
        if (category != null) {
            Category existingCategory = categoryService.getCategoryByTitle(category.getTitle());
            if (existingCategory == null) {
                // Create new category if it does not exist
                existingCategory = categoryService.createCategory(category.getTitle());
            }
            // Set the existing or newly created category to the product
            product.setCategory(existingCategory);
        }

        // Set timestamps
        LocalDateTime current = LocalDateTime.now();
        product.setCreatedAt(current);
        product.setUpdatedAt(current);

        // Save the product
        Product pr=productRepo.save(product);
        // if product is not saved
        if(pr==null){
            throw new ProductNotFoundException("Product not saved", 406);
        }
        return pr;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product fetchedProduct = productRepo.findProductById(id);
        if(fetchedProduct==null || fetchedProduct.isDeleted()){
            throw new ProductNotFoundException("Product not found for updation", HttpStatus.NOT_FOUND.value());
        }
        // update only that attributes that are not null
        if(product.getTitle()!=null){
            fetchedProduct.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null){
            fetchedProduct.setDescription(product.getDescription());
        }
        if(product.getPrice()!=0){
            fetchedProduct.setPrice(product.getPrice());
        }

        Category category = product.getCategory();
        if (category != null) {
            Category existingCategory = categoryService.getCategoryByTitle(category.getTitle());
            if (existingCategory == null) {
                // Create new category if it does not exist
                existingCategory = categoryService.createCategory(category.getTitle());
            }
            // Set the existing or newly created category to the product
            product.setCategory(existingCategory);
        }

        fetchedProduct.setUpdatedAt(current);
        return productRepo.save(fetchedProduct);
    }

    @Override
    public String deleteProduct(long id) {
        Product product = productRepo.findProductById(id);

        if(!productRepo.existsById(id) || product.isDeleted()){
            throw new ProductNotFoundException("Product not available", HttpStatus.NOT_FOUND.value());
        }

        product.setDeleted(true);
        product.setDeletedAt(current);
        productRepo.save(product);
        return "Product deleted successfully";
    }

    @Override
    public List<Product> getProductsByCategory(String title) {
        List<Product> pr=productRepo.findAllProductsByCategory(title);
        // check if category exists
        if(pr.isEmpty()){
            throw new ProductNotFoundException("No products available", HttpStatus.NOT_FOUND.value());
        }
        return pr;
    }
}
