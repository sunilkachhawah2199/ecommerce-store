package com.ecommerce.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private ResponseCategoryDto category;
}
