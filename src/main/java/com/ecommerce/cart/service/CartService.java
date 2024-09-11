package com.ecommerce.cart.service;

import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.Item;

import java.util.List;

public interface CartService {
    public List<Cart> getAllCarts();
    public Cart createCart(long userId);
    public Cart addItemToCart(long cartId, Item item);
    public Cart getCartById(long cartId);
    public Cart updateCart(long cartId, long itemId, Item item);
    public Cart removeProduct(long cartId, long itemId);
    public Cart getUserCart(long userId);
}
