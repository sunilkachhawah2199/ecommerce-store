package com.ecommerce.product.dto;


// this is dto for that which we will get from fake store api
// we will map this dto with our product dto

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreResponseDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
