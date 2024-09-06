package com.ecommerce.Service.impl;

import com.ecommerce.Service.CategoryService;
import com.ecommerce.Service.ProductService;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.Projection.ProductProjection;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service("SelfService")
public class ProductServiceSelfImpl implements ProductService {

    private ProductRepo productRepo;
    private CategoryService categoryService;
    private RestTemplate restTemplate;

    LocalDateTime current= LocalDateTime.now();

    public ProductServiceSelfImpl(ProductRepo productRepo,
                                  RestTemplate restTemplate,
                                  CategoryService categoryService) {
        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
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

        if(product.isDeleted()){
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @Override
    public Product cerateProduct(Product product) {
        // check category exists
        Category category=product.getCategory();

        Boolean categoryExists= categoryService.existsByTitle(category.getTitle());


        // if category does not exist, create it
        if(!categoryExists){
            categoryService.createCategory(category);
        }

        product.setCreatedAt(current);

        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product fetchedProduct = productRepo.findProductById(id);
        if(fetchedProduct==null || fetchedProduct.isDeleted()){
            throw new ProductNotFoundException("Product not available", 404);
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

        Category category=product.getCategory();
        if(category!=null){
            // if category does not exist, create it
            Boolean categoryExists= categoryService.existsByTitle(category.getTitle());
            if(!categoryExists){
                categoryService.createCategory(category);
                fetchedProduct.setCategory(category);
            }else{
                fetchedProduct.setCategory(category);
            }
        }
        fetchedProduct.setUpdatedAt(current);
        return productRepo.save(fetchedProduct);
    }

    @Override
    public String deleteProduct(long id) {
        Product product = productRepo.findProductById(id);

        if(!productRepo.existsById(id) || product.isDeleted()){
            throw new ProductNotFoundException("Product not available", 404);
        }

        product.setDeleted(true);
        product.setDeletedAt(current);
        productRepo.save(product);
        return "Product deleted successfully";
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }




}
