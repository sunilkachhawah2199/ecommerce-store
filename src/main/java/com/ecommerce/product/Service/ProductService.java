package com.ecommerce.product.Service;

import com.ecommerce.product.models.Product;

import java.util.List;

public interface ProductService {

    // get all products
    public List<Product> getProducts();

    // get product by id
    public Product getProductsById(long id);

    // create product
    public Product createProduct(Product product);

    // update product
    public Product updateProduct(long id, Product product);

    // delete product
    public String deleteProduct(long id);

    // get product by category
    public List<Product> getProductsByCategory(String category);







}