package com.ecommerce.controller;


import com.ecommerce.Service.ProductService;
import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.dto.ResponseDetails;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductController(@Qualifier("SelfService") ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }



    // check api
    @GetMapping("/check")
    public String check() {
        return "Hello World";
    }

    // get all products
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> products=productService.getProducts();

        // map product to product dto
        List<ProductDto> response=new ArrayList<>();

        for(Product product: products){
            ProductDto productDto=mapProductToProductDto(product);
            response.add(productDto);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {

        Product product= productService.getProductsById(id);
        ProductDto productDto=mapProductToProductDto(product);
        return ResponseEntity.ok(productDto);
    }

    // create product
    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        Product product=mapProductDtoToProduct(productDto);
        Product createdProduct=productService.cerateProduct(product);
        ProductDto createdProductDto=mapProductToProductDto(createdProduct);
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
    }


    // create product list
    @PostMapping("/list")
    public List<ProductDto> cerateProductList(@RequestBody List<ProductDto> productDtoList){
        List<ProductDto> response=new ArrayList<>();
        for(ProductDto productDto: productDtoList){
            Product product=mapProductDtoToProduct(productDto);
            Product createdProduct=productService.cerateProduct(product);
            ProductDto createdProductDto=mapProductToProductDto(createdProduct);
            response.add(createdProductDto);
        }
        return response;
    }


    // update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") long id, @RequestBody ProductDto productDto){
        Product product=mapProductDtoToProduct((productDto));
        product=productService.updateProduct(id, product);
        ProductDto updatedProductDto=mapProductToProductDto(product);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    // delete product
    @DeleteMapping("/{id}")
    public  ResponseEntity<ResponseDetails> deleteProduct(@PathVariable("id") long id){
        String message=productService.deleteProduct(id);
        ResponseDetails responseDetails=new ResponseDetails(message, LocalDateTime.now());

        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable("category") String category){
        List<Product> products=productService.getProductsByCategory(category);
        List<ProductDto> response=new ArrayList<>();
        for(Product product: products){
            ProductDto productDto=mapProductToProductDto(product);
            response.add(productDto);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    // get all catregories
//    @GetMapping("/categories")
//    public ResponseEntity<List<String>> getAllCategories(){
//        List<String> categories=productService.getCategories();
//        return new ResponseEntity<>(categories, HttpStatus.OK);
//    }

    // ------------------------------------------------- mapper class ---------------------------------------------------

    // will use model mapper class to map our product model class to product dto
    public ProductDto mapProductToProductDto(Product product ){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());

        CategoryDto categoryDto= new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setTitle(product.getCategory().getTitle());

        productDto.setCategory(categoryDto);

        return productDto;
    }

    // map product dto to product
    public Product mapProductDtoToProduct(ProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        Category category=new Category();
        category.setId(productDto.getCategory().getId());
        category.setTitle(productDto.getCategory().getTitle());
        product.setCategory(category);
        return product;
    }






}
