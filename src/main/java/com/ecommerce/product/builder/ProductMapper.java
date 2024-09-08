package com.ecommerce.product.builder;

import com.ecommerce.product.dto.*;
import com.ecommerce.product.models.Category;
import com.ecommerce.product.models.Product;
import org.springframework.stereotype.Component;

@Component // this is a bean
public class ProductMapper {
    // --------------------------------mapper to map product and our database objects--------------------------------

    // request product map with product
    public Product mapProductRequestToProduct(RequestProductDto productRequestDto){
        Product product=new Product();
        Category category=new Category();



        if(productRequestDto.getTitle()!=null){
            product.setTitle(productRequestDto.getTitle());
        }
        if(productRequestDto.getPrice()!=0){
            product.setPrice(productRequestDto.getPrice());
        }
        if(productRequestDto.getDescription()!=null){
            product.setDescription(productRequestDto.getDescription());
        }
        if(productRequestDto.getImageUrl()!=null){
            product.setImageUrl(productRequestDto.getImageUrl());
        }
        if(productRequestDto.getCategory()!=null){
            RequestCategoryDto cat=productRequestDto.getCategory();
            if(cat.getTitle()!=null){
                category.setTitle(cat.getTitle());
            }
            product.setCategory(category);
        }
        return product;
    }

    // map product with response product dto
    public ResponseProductDto mapProductToResponseProductDto(Product product){
        ResponseProductDto res=new ResponseProductDto();
        ResponseCategoryDto cat=new ResponseCategoryDto();

        res.setId(product.getId());
        res.setTitle(product.getTitle());
        res.setDescription(product.getDescription());
        res.setPrice(product.getPrice());
        res.setImageUrl(product.getImageUrl());

        cat.setId(product.getCategory().getId());
        cat.setTitle(product.getCategory().getTitle());
        res.setCategory(cat);
        return res;
    }

    // --------------------------------- mapper to map FakeStoreDto to Product
    public Product mapFakeStoreDtoToProduct(FakeStoreResponseDto fakeStoreResponseDto){
        Product product=new Product();

        Category category=new Category();

        category.setTitle(fakeStoreResponseDto.getCategory());

        product.setId(fakeStoreResponseDto.getId());
        product.setTitle(fakeStoreResponseDto.getTitle());
        product.setPrice(fakeStoreResponseDto.getPrice());
        product.setCategory(category); // we need arguement constructor in Category class
        product.setDescription(fakeStoreResponseDto.getDescription());
        product.setImageUrl(fakeStoreResponseDto.getImage());
        return product;
    }

    // mapper to map Product to FakeStoreDto
    public FakeStoreRequestDto mapToFake(Product product){
        FakeStoreRequestDto fake=new FakeStoreRequestDto();
        fake.setTitle(product.getTitle());
        fake.setPrice(product.getPrice());
        fake.setCategory(product.getCategory().getTitle());
        fake.setDescription(product.getDescription());
        fake.setImage(product.getImageUrl());

        return fake;
    }
}
