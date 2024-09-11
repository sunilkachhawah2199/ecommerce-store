package com.ecommerce.product.controller;


import com.ecommerce.product.Service.CategoryService;
import com.ecommerce.product.Service.ProductService;
import com.ecommerce.product.builder.ProductMapper;
import com.ecommerce.product.dto.RequestProductDto;
import com.ecommerce.product.dto.ResponseProductDto;
import com.ecommerce.product.dto.ResponseDetails;
import com.ecommerce.product.models.Category;
import com.ecommerce.product.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/products")
public class ProductController {

    // dependency injection , no need to create object of RestTemplate
    private final ProductService productService;
    private final CategoryService categoryService;
    private ProductMapper productMapper;
    public ProductController(@Qualifier("SelfService") ProductService productService,
                             CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }


    // get all products
    @GetMapping("/")
    @Cacheable(value = "product")
    public ResponseEntity<List<ResponseProductDto>> getAllProducts(){
        List<Product> products=productService.getProducts();

        // map product to product dto
        List<ResponseProductDto> response=new ArrayList<>();

        for(Product product: products){
            ResponseProductDto responseProductDto =productMapper.mapProductToResponseProductDto(product);
            response.add(responseProductDto);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // get product by id
    @GetMapping("/{id}")
    @Cacheable(value = "product", key = "#id")
    public ResponseEntity<ResponseProductDto> getProductById(@PathVariable("id") Long id) {

        Product product= productService.getProductsById(id);
        ResponseProductDto responseProductDto =productMapper.mapProductToResponseProductDto(product);
        return ResponseEntity.ok(responseProductDto);
    }

    // create product
    @PostMapping("/")
    // create new cache with key as product id
    @CachePut(value = "product", key = "#result.id")
    public ResponseEntity<ResponseProductDto> createProduct(@RequestBody RequestProductDto requestProductDto){
        Product product=productMapper.mapProductRequestToProduct(requestProductDto);
        Product createdProduct=productService.createProduct(product);
        ResponseProductDto createdResponseProductDto =productMapper.mapProductToResponseProductDto(createdProduct);
        return new ResponseEntity<>(createdResponseProductDto, HttpStatus.CREATED);
    }


    // create product list
    @PostMapping("/list")
    @Cacheable(value = "product")
    public ResponseEntity<List<ResponseProductDto>> cerateProductList(@RequestBody List<RequestProductDto> requestProductDtoList){
        List<ResponseProductDto> response=new ArrayList<>();
        for(RequestProductDto requestProductDto : requestProductDtoList){
            Product product=productMapper.mapProductRequestToProduct(requestProductDto);
            Product createdProduct=productService.createProduct(product);

            ResponseProductDto createdResponseProductDto =productMapper.mapProductToResponseProductDto(createdProduct);
            response.add(createdResponseProductDto);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // update product
    @PutMapping("/{id}")
    @CachePut(value = "product", key = "#id")
    public ResponseEntity<ResponseProductDto> updateProduct(@PathVariable("id") long id, @RequestBody RequestProductDto requestProductDto){
        // map request dto to product
        Product product=productMapper.mapProductRequestToProduct(requestProductDto);

        // calling service layer to update product
        product=productService.updateProduct(id, product);

        ResponseProductDto updatedResponseProductDto =productMapper.mapProductToResponseProductDto(product);
        return new ResponseEntity<>(updatedResponseProductDto, HttpStatus.OK);
    }

    // delete product
    @DeleteMapping("/{id}")
    @CacheEvict(value = "product", key = "#id")
    public  ResponseEntity<ResponseDetails> deleteProduct(@PathVariable("id") long id){
        String message=productService.deleteProduct(id);
        ResponseDetails responseDetails=new ResponseDetails(message, LocalDateTime.now());

        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }



    // get all categories
    @GetMapping("/categories")
    @Cacheable(value = "category")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories=categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // get products by category
    @GetMapping("/category/{category}")
    @Cacheable(value = "product", key = "#category")
    public ResponseEntity<List<ResponseProductDto>> getProductsByCategory(@PathVariable("category") String category){
        List<Product> products=productService.getProductsByCategory(category);
        List<ResponseProductDto> response=new ArrayList<>();
        for(Product product: products){
            ResponseProductDto responseProductDto =productMapper.mapProductToResponseProductDto(product);
            response.add(responseProductDto);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
