package com.ecommerce.models;


import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product extends  BaseModel{


    private String title;

    private String description;


    private double price;

    private String imageUrl;


    // we add on ManytoOne relationship on the product side because 1 product can have only 1 category
    // we can put @OneTomMany in the category will work same like this. --> but we have to use mappedby attribute in the @OneToMany annotation and name will be this category there
    @ManyToOne(cascade = CascadeType.PERSIST)

    private Category category;
}
