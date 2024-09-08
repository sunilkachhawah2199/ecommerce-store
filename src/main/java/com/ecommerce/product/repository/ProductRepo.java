package com.ecommerce.product.repository;

import com.ecommerce.product.models.Product;
import com.ecommerce.product.repository.Projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = false")
    List<Product> findAllNotDeleted();


    @Query("select  p.id, p.price from Product p where p.id = :id")
    ProductProjection getPriceById(@Param("id") Long id); // only 1 unique for each product id\

    @Query("select p.id, p.price from Product p where p.title = :title")
    List<ProductProjection> getPriceByTitle(@Param("title") String title);


    @Query("SELECT p from Product p join p.category c where c.title = :title")
    List<Product> findAllProductsByCategory(String title);
}
