package com.ecommerce.cart.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long user_id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JsonIgnoreProperties("cart") // to avoid infinite recursion
    private List<Item> items;


}
