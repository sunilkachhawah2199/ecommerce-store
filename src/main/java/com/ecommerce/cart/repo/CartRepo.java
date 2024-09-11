package com.ecommerce.cart.repo;

import com.ecommerce.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findById(long id);
}
