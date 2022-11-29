package com.lciresh.cartservice.service;

import com.lciresh.cartservice.exceptions.DataExistingException;
import com.lciresh.cartservice.exceptions.NoDataAvailableException;
import com.lciresh.cartservice.model.Cart;
import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.repository.CartRepository;
import com.lciresh.cartservice.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class CartService implements CartServiceInterface{

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Cart createNewCart(String username) {
        Optional<Cart> cartOptional = cartRepository.getCartByUsername(username);
        if (cartOptional.isPresent()) {
            throw new DataExistingException("User already have a Cart.");
        }

        Cart cart = Cart.builder()
                .username(username)
                .items(new ArrayList<>())
                .build();
        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Cart addItemToCart(Long itemId, String username) {
        Optional<Cart> cartOptional = cartRepository.getCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NoDataAvailableException("User does not exist a Cart.");
        }

        Cart cart = cartRepository.findCartByUsername(username);
        Item item = itemRepository.findItemByItemId(itemId);

        cart.getItems().add(item);
        return cart;
    }

    @Override
    public Cart removeItemFromCart(Long itemId, String username) {
        Optional<Cart> cartOptional = cartRepository.getCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NoDataAvailableException("User does not exist a Cart.");
        }

        Cart cart = cartRepository.findCartByUsername(username);
        Item item = itemRepository.findItemByItemId(itemId);

        cart.getItems().remove(item);
        return cart;
    }
}
