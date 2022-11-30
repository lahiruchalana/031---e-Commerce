package com.lciresh.cartservice.service;

import com.lciresh.cartservice.model.Cart;

public interface CartServiceInterface {
    
    Cart createNewCart(String username);

    Cart addItemToCart(Long itemId, String username);

    Cart removeItemFromCart(Long itemId, String username);

    Cart getCart(String token);
}
