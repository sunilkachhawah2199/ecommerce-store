package com.ecommerce.repository.Projection;

// projection used when we return specific attributes of model from repo layer
public interface ProductProjection {

    Integer getId();
    Double getPrice();
}
