package com.ecommerce.cart.service.Impl;

import com.ecommerce.cart.exceptions.ResourceNotFound;
import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.repo.CartRepo;
import com.ecommerce.cart.repo.ItemRepo;
import com.ecommerce.cart.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    // dependency injection
    private CartRepo cartRepo;
    private ItemRepo itemRepo;
    public CartServiceImpl(CartRepo cartRepo,
                           ItemRepo itemRepo) {
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepo.findAll();
        if(carts.isEmpty()){
            throw new RuntimeException("No carts found");
        }
        return carts;
    }

    @Override
    public Cart createCart(long userId) {
        Cart cart = new Cart();
        cart.setUser_id(userId);
        return cartRepo.save(cart);
    }

    @Override
    public Cart addItemToCart(long cartId, Item item) {
        Cart fetchCart = getCartById(cartId);
        if (fetchCart == null) {
            throw new RuntimeException("Cart not found");
        }
        item.setCart(fetchCart);
        Item saved= itemRepo.save(item);
        return getCartById(cartId);
    }


    @Override
    public Cart getCartById(long cartId) {
        Cart c=cartRepo.findById(cartId);
        if(c==null){
            throw new ResourceNotFound("Cart not found", 404);
        }
        return c;
    }

    @Override
    public Cart updateCart(long cartId, long itemId, Item item) {
        // check cart exists or not
        Cart fetched= getCartById(cartId);
        if(fetched==null){
            throw new RuntimeException("Cart not found");
        }
        Item fetchedItem = itemRepo.findById(itemId).orElseThrow(() -> new ResourceNotFound("Item not found", 404));

        // update item
        fetchedItem.setQuantity(item.getQuantity());
        itemRepo.save(fetchedItem);
        return getCartById(cartId);
    }

    @Override
    public Cart removeProduct(long cartId, long itemId) {
        Cart fetched = getCartById(cartId);
        if(fetched==null){
            throw new ResourceNotFound("Cart not found",404);
        }
        Item fetchedItem = itemRepo.findById(itemId).orElseThrow(() -> new ResourceNotFound("Item not found", 404));

        // Remove the item from the cart's items list
        fetched.getItems().remove(fetchedItem);

        // Delete the item from the repository
        itemRepo.deleteById(itemId);

        // Save the updated cart
        cartRepo.save(fetched);

        return getCartById(cartId);
    }

    @Override
    public Cart getUserCart(long userId) {
        return null;
    }
}
