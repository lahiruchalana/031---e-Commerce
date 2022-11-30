package com.lciresh.cartservice.service;

import com.lciresh.cartservice.dto.UserAuthDTO;
import com.lciresh.cartservice.exceptions.DataExistingException;
import com.lciresh.cartservice.exceptions.NoDataAvailableException;
import com.lciresh.cartservice.model.Cart;
import com.lciresh.cartservice.model.Item;
import com.lciresh.cartservice.repository.CartRepository;
import com.lciresh.cartservice.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class CartService implements CartServiceInterface{

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;

    public CartService(CartRepository cartRepository, ItemRepository itemRepository, RestTemplate restTemplate) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Cart createNewCart(String token) {
        UserAuthDTO userAuth = restTemplate.getForObject("http://AUTH-SERVICE/api/app-users/user/"+token+"/get-username-and-roles", UserAuthDTO.class);
        String username = userAuth.getUsername();

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
    public Cart addItemToCart(Long itemId, String token) {
        UserAuthDTO userAuth = restTemplate.getForObject("http://AUTH-SERVICE/api/app-users/user/"+token+"/get-username-and-roles", UserAuthDTO.class);
        String username = userAuth.getUsername();

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
    public Cart removeItemFromCart(Long itemId, String token) {
        UserAuthDTO userAuth = restTemplate.getForObject("http://AUTH-SERVICE/api/app-users/user/"+token+"/get-username-and-roles", UserAuthDTO.class);
        String username = userAuth.getUsername();

        Optional<Cart> cartOptional = cartRepository.getCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NoDataAvailableException("User does not exist a Cart.");
        }

        Cart cart = cartRepository.findCartByUsername(username);
        Item item = itemRepository.findItemByItemId(itemId);

        cart.getItems().remove(item);
        return cart;
    }

    @Override
    public Cart getCart(String token) {
        UserAuthDTO userAuth = restTemplate.getForObject("http://AUTH-SERVICE/api/app-users/user/"+token+"/get-username-and-roles", UserAuthDTO.class);
        String username = userAuth.getUsername();

        Optional<Cart> cartOptional = cartRepository.getCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NoDataAvailableException("User does not exist a Cart.");
        }

        return cartRepository.findCartByUsername(username);
    }
}
