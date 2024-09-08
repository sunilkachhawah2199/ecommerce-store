package com.ecommerce.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDto {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private RequestCategoryDto category;
}
