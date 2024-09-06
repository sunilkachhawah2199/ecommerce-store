package com.ecommerce.builder;

import com.ecommerce.dto.FakeStoreRequestDto;
import com.ecommerce.dto.FakeStoreResponseDto;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import org.springframework.stereotype.Component;

@Component // this is a bean
public class ProductMapper {
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
