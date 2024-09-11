package com.ecommerce.cart.repo;

import com.ecommerce.cart.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {

}
