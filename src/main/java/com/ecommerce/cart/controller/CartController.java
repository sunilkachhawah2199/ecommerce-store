package com.ecommerce.cart.controller;


import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    // inject Service layer interface not a class
    private CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAll(){
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<Cart> createcart(@PathVariable("user_id") long userId){
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{cart_id}/add")
    public ResponseEntity<Cart> addItemToCart(@PathVariable("cart_id") long cartId, @RequestBody Item item){
        Cart res = cartService.addItemToCart(cartId, item);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{cart_id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("cart_id") long cartId){
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{cart_id}/update/{item_id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("cart_id") long cartId, @PathVariable("item_id") long itemId, @RequestBody Item item){
        Cart cart = cartService.updateCart(cartId, itemId, item);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cart_id}/remove/{item_id}")
    public ResponseEntity<Cart> removeProduct(@PathVariable("cart_id") long cartId, @PathVariable("item_id") long itemId){
        Cart cart = cartService.removeProduct(cartId, itemId);
        return ResponseEntity.ok(cart);
    }
}
