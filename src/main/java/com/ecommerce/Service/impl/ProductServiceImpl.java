package com.ecommerce.Service.impl;

import com.ecommerce.builder.ProductMapper;
import com.ecommerce.Service.ProductService;
import com.ecommerce.dto.FakeStoreRequestDto;
import com.ecommerce.dto.FakeStoreResponseDto;
import com.ecommerce.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    // this is a configured as a bean in ApplicationConfiguration.java
    private RestTemplate restTemplate;
    ProductMapper productMapper;

    // dependency injection , no need to create object of RestTemplate
    public ProductServiceImpl(RestTemplate restTemplate, ProductMapper productMapper) {
        this.restTemplate = restTemplate;
        this.productMapper = productMapper;
    }


    // global variable
    private String uri="https://fakestoreapi.com/products";


    // get all products
    @Override
    public List<Product> getProducts() {
        ResponseEntity<List<FakeStoreResponseDto>> fakeProductList=restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreResponseDto>>() {});
        // list of products
        List<Product> products=new ArrayList<>();

        //  iterate over fakeProductList and map each FakeStoreDto to Product entity
        for(FakeStoreResponseDto fakeStoreResponseDto : fakeProductList.getBody()){
            Product pro=productMapper.mapFakeStoreDtoToProduct(fakeStoreResponseDto);
            products.add(pro);
        }

        return products;
    }

    // get product by id
    @Override
    public Product getProductsById(long id) {
        // we will get product by id from fakestore api we will map that with our Product dto
        // we generally return direct model class from service layer and we map with any dto according to our requirement in controller layer

        // we will use RestTemplate to call api
        // we will use getForEntity method of RestTemplate to call api
        // return type of getForEntity is ResponseEntity
        // parameter of getForEntity is url, response type, path variable



        // url for get product by id
        String url= uri+"/"+id; // to make it dynamic we can use string concatenation

        // parameter of getForEntity is url, response type class
        ResponseEntity<FakeStoreResponseDto> response=restTemplate.getForEntity(url, FakeStoreResponseDto.class);

        // all data will be in response body
        FakeStoreResponseDto fakeStoreResponseDto =response.getBody();

        if(fakeStoreResponseDto ==null){
            return null;
        }
        // we will map this fakeStoreDto with our Product model class
        Product product=productMapper.mapFakeStoreDtoToProduct(fakeStoreResponseDto);

        return product;
    }

    @Override
    public Product cerateProduct(Product product) {
        // change product in FakeStoreRequestDto

        FakeStoreRequestDto fake= productMapper.mapToFake(product); // without id

        // when product is created it will return FakeStoreResponseDto (with id)

        FakeStoreResponseDto fakeStoreResponseDto= restTemplate.postForObject(uri, fake, FakeStoreResponseDto.class);
        System.out.println(fakeStoreResponseDto);
        // map FakeStoreResponseDto to Product
        Product pro = productMapper.mapFakeStoreDtoToProduct(fakeStoreResponseDto);
        return pro;
    }

    @Override
    public Product updateProduct(long id, Product product) {

        // change product in FakeStoreRequestDto
        FakeStoreRequestDto fake=productMapper.mapToFake(product);

        String url=uri+"/"+id;
        // when product is updated it will return FakeStoreResponseDto (with id)
        ResponseEntity<FakeStoreResponseDto> res=restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(fake),
                FakeStoreResponseDto.class
        );
        FakeStoreResponseDto fakeRes= res.getBody();

        // map FakeStoreResponseDto to Product
        Product pro=productMapper.mapFakeStoreDtoToProduct(fakeRes);
        return pro;
    }

    @Override
    public String deleteProduct(long id) {

        HttpEntity<FakeStoreResponseDto> fakeRes=restTemplate.exchange(
                uri+"/"+id,
                HttpMethod.DELETE,
                null,
                FakeStoreResponseDto.class
        );
        FakeStoreResponseDto fakeRespo=fakeRes.getBody();
        if(fakeRespo==null){
            return "Product not found";
        }
        return "Product deleted";
    }

    // get products by category
    @Override
    public List<Product> getProductsByCategory(String category) {
        ResponseEntity<List<FakeStoreResponseDto>> fakeResponses=restTemplate.exchange(
                uri + "/category/" + category,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreResponseDto>>() {}
        );
        List<Product> pro=new ArrayList<>();
        for(FakeStoreResponseDto fakeStoreResponseDto: fakeResponses.getBody()){
            Product product=productMapper.mapFakeStoreDtoToProduct(fakeStoreResponseDto);
            pro.add(product);
        }
        return pro;
    }

//    @Override
//    public List<String> getCategories() {
//        HttpEntity<List<String>> categories=restTemplate.exchange(
//                uri + "/categories",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<String>>() {}
//        );
//        List<String> res=new ArrayList<>();
//        for(String r: categories.getBody()){
//            res.add(r);
//        }
//        return res;
//    }



}
